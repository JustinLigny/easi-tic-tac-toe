import {Component, OnInit} from '@angular/core';
import {Game} from '../../../shared/models/game';
import {GameService} from '../../../shared/services/game.service';
import {PlacePawnDto} from '../../../shared/dto/place-pawn-dto';
import {Button} from 'primeng/button';
import {NgClass, NgForOf, NgIf, NgStyle, NgSwitch, NgSwitchCase} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [
    Button,
    NgStyle,
    NgForOf,
    NgIf,
    NgSwitchCase,
    NgSwitch,
    NgClass
  ],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent implements OnInit {

  game!: Game;

  constructor(
    private _gameService: GameService,
    private _router: Router) {
  }

  ngOnInit(): void {
    this.getCurrentGame()
  }

  getCurrentGame(): void {
    this._gameService.getCurrentGame().subscribe(game => {
      this.game = game;
    });
  }

  onCellClicked(row: number, column: number): void {
    const placePawnDto: PlacePawnDto = {
      row,
      column
    };

    this._gameService.placePawn(placePawnDto).subscribe({
      next: (game: Game) => {
        this.game = game;
      },
      error: (err: Error) => {
        console.log(err);
      }
    });
  }

  restartGame() {
    this._gameService.restart().subscribe({
      next: (game: Game) => {
        this.game = game;
      },
      error: (err: Error) => {
        console.log(err);
      }
    });
  }

  backToMenu() {
    void this._router.navigate(['/menu']);
  }
}
