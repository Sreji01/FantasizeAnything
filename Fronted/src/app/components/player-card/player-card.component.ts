import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-player-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './player-card.component.html',
  styleUrls: ['./player-card.component.css']
})
export class PlayerCardComponent {
  @Input() playerLastName: string = '';
  @Input() playerImage: string = '';

  defaultImage = 'assets/default-player.png'

  get playerImageUrl() {
    return this.playerImage || this.defaultImage;
  }
}
