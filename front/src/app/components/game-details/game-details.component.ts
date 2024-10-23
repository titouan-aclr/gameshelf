import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Game } from '../../models/game.model';
import { DecodeHtmlPipe } from '../../pipes/decode-html.pipe';
import { GamesService } from '../../services/games.service';

@Component({
  selector: 'app-game-details',
  standalone: true,
  imports: [DecodeHtmlPipe],
  templateUrl: './game-details.component.html',
  styleUrl: './game-details.component.scss'
})
export class GameDetailsComponent implements OnInit {
  game!: Game;

  constructor(
    private gamesService: GamesService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const gameId: number = this.route.snapshot.params['id'];
    this.game = this.gamesService.getGameById(gameId);
  }
}
