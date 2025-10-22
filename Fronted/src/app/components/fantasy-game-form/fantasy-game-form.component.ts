import {Component} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators, ReactiveFormsModule} from '@angular/forms';
import {NgIf, NgForOf} from '@angular/common';
import {PlayerFormComponent} from "../player-form/player-form.component";
import {PlayerCardComponent} from "../player-card/player-card.component";
import {FantasyGameService} from "../../services/fantasy-game.service";

@Component({
  selector: 'app-fantasy-game-form',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, NgForOf, PlayerFormComponent, PlayerCardComponent],
  templateUrl: './fantasy-game-form.component.html',
  styleUrls: ['./fantasy-game-form.component.css']
})
export class FantasyGameFormComponent {
  fantasyForm: FormGroup;
  newRuleForm: FormGroup;
  showPlayerForm = false;
  showScoringRuleForm = false;
  editingIndex: number | null = null;
  editPlayerIndex: number | null = null;
  playerBeingEdited: any = null;

  constructor(private fb: FormBuilder, private fantasyGameService: FantasyGameService) {
    this.fantasyForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      numberOfPlayersPerTeam: [''],
      budget: [''],
      scoringRules: this.fb.array([]),
      players: this.fb.array([])
    });

    this.newRuleForm = this.fb.group({
      description: [''],
      numberOfPoints: ['']
    });
  }

  get players(): FormArray {
    return this.fantasyForm.get('players') as FormArray;
  }

  get scoringRules(): FormArray {
    return this.fantasyForm.get('scoringRules') as FormArray;
  }

  initNewRuleForm(): void {
    this.newRuleForm.reset();
    this.editingIndex = null;
  }

  toggleScoringForm(): void {
    this.showScoringRuleForm = true;
  }

  addScoringRuleToList(): void {
    const ruleValue = this.newRuleForm.value;
    if (!ruleValue?.description || !ruleValue?.numberOfPoints) return;

    const ruleGroup = this.fb.group({
      description: [ruleValue.description],
      numberOfPoints: [ruleValue.numberOfPoints]
    });

    this.scoringRules.push(ruleGroup);
    this.cancelScoringForm();
  }

  editScoringRule(index: number): void {
    const selectedRule = this.scoringRules.at(index).value;
    this.newRuleForm.patchValue(selectedRule);
    this.showScoringRuleForm = true;
    this.editingIndex = index;
  }

  saveEditedScoringRule(): void {
    if (this.editingIndex !== null) {
      const updatedValue = this.newRuleForm.value;
      if (!updatedValue?.description || !updatedValue?.numberOfPoints) return;

      this.scoringRules.at(this.editingIndex).patchValue(updatedValue);
    }
    this.cancelScoringForm();
  }

  removeScoringRule(index: number): void {
    this.scoringRules.removeAt(index);
  }

  cancelScoringForm(): void {
    this.showScoringRuleForm = false;
    this.editingIndex = null;
    this.newRuleForm.reset();
  }

  addPlayerToArray(playerData: any): void {
    this.players.push(this.fb.group(playerData));
    this.showPlayerForm = false;
  }

  editPlayer(index: number) {
    this.editPlayerIndex = index;
    this.playerBeingEdited = this.players.at(index).value;
    this.showPlayerForm = true;
  }

  saveEditedPlayer(updatedData: any) {
    if (this.editPlayerIndex !== null) {
      this.players.at(this.editPlayerIndex).patchValue(updatedData);
      this.editPlayerIndex = null;
    }
    this.showPlayerForm = false;
    this.playerBeingEdited = null;
  }

  removePlayer(index: number) {
    this.players.removeAt(index);
  }

  onSubmit(): void {
    if (this.fantasyForm.invalid) return;

    this.fantasyGameService.createFantasyGame(this.fantasyForm.value).subscribe({
      next: (createdGame) => {
        console.log('Created:', createdGame);
        alert('Fantasy game has been successfully created.');
      },
      error: (err) => {
        console.error('Error:', err);
        alert('An error occurred while creating the fantasy game. Please try again.');
      }
    });
  }
}
