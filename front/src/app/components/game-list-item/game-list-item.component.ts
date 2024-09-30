import { Component, Input } from '@angular/core';
import { Game } from '../../models/game.model';

@Component({
  selector: 'app-game-list-item',
  standalone: true,
  imports: [],
  templateUrl: './game-list-item.component.html',
  styleUrl: './game-list-item.component.scss'
})
export class GameListItemComponent {
  @Input() game!: Game;
}
