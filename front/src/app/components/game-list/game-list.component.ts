import { Component, OnInit } from '@angular/core';
import { Game } from '../../models/game.model';
import { GamesService } from '../../services/games.service';
import { GameListItemComponent } from '../game-list-item/game-list-item.component';

@Component({
  selector: 'app-game-list',
  standalone: true,
  imports: [GameListItemComponent],
  templateUrl: './game-list.component.html',
  styleUrl: './game-list.component.scss'
})
export class GameListComponent implements OnInit {
  games!: Game[];

  constructor(private gameService: GamesService) { }

  ngOnInit(): void {
    this.games = this.gameService.getGames();
  }
}
