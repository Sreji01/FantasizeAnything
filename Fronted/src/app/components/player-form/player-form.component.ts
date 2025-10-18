import { Component, EventEmitter, Input, Output, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgForOf, NgIf } from '@angular/common';

@Component({
  selector: 'app-player-form',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, NgForOf, NgIf],
  templateUrl: './player-form.component.html',
  styleUrls: ['./player-form.component.css']
})
export class PlayerFormComponent implements OnChanges {
  @Input() initialData: any = null;
  @Output() add = new EventEmitter<any>();
  @Output() cancel = new EventEmitter<void>();
  @Output() saveEdit = new EventEmitter<any>();

  playerForm: FormGroup;
  showCharacteristicInputs = false;
  tempCharacteristic = { name: '', value: '' };
  editIndex: number | null = null;

  constructor(private fb: FormBuilder) {
    this.playerForm = this.fb.group({
      firstName: [''],
      lastName: [''],
      price: [''],
      image: [''],
      characteristics: this.fb.array([])
    });
  }

  get characteristics(): FormArray {
    return this.playerForm.get('characteristics') as FormArray;
  }

  toggleCharacteristicInput(): void {
    this.showCharacteristicInputs = true;
    this.editIndex = null;
    this.tempCharacteristic = { name: '', value: '' };
  }

  cancelCharacteristicInput(): void {
    this.showCharacteristicInputs = false;
    this.tempCharacteristic = { name: '', value: '' };
    this.editIndex = null;
  }

  confirmAddOrEditCharacteristic(): void {
    const { name, value } = this.tempCharacteristic;
    if (!name.trim() || !value.trim()) return;

    const characteristicGroup = this.fb.group({ name: [name], value: [value] });

    if (this.editIndex !== null) {
      this.characteristics.setControl(this.editIndex, characteristicGroup);
      this.editIndex = null;
    } else {
      this.characteristics.push(characteristicGroup);
    }

    this.tempCharacteristic = { name: '', value: '' };
    this.showCharacteristicInputs = false;
  }

  editCharacteristic(index: number): void {
    const char = this.characteristics.at(index).value;
    this.tempCharacteristic = { ...char };
    this.editIndex = index;
    this.showCharacteristicInputs = true;
  }

  removeCharacteristic(index: number): void {
    this.characteristics.removeAt(index);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['initialData'] && this.initialData) {
      this.playerForm.patchValue(this.initialData);
      this.characteristics.clear();
      if (this.initialData.characteristics) {
        for (const char of this.initialData.characteristics) {
          this.characteristics.push(this.fb.group({
            name: [char.name],
            value: [char.value]
          }));
        }
      }
    }
  }

  onFileSelected(event: any): void {
    const file = event.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.playerForm.patchValue({ image: reader.result });
      };
      reader.readAsDataURL(file);
    }
  }

  onSubmit(): void {
    if (this.initialData) {
      this.saveEdit.emit(this.playerForm.value);
    } else {
      this.add.emit(this.playerForm.value);
    }
  }
}
