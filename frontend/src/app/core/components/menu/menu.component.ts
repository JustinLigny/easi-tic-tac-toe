import {Component} from '@angular/core';
import {PanelModule} from 'primeng/panel';
import {CardModule} from 'primeng/card';
import {Button} from 'primeng/button';
import {NgClass, NgIf} from '@angular/common';
import {SelectButtonChangeEvent, SelectButtonModule} from 'primeng/selectbutton';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputNumberModule} from 'primeng/inputnumber';
import {GameService} from '../../../shared/services/game.service';
import {StartGameDto} from '../../../shared/dto/start-game-dto';
import {InputTextModule} from 'primeng/inputtext';
import {Router} from '@angular/router';
import {MessageService} from 'primeng/api';

type PlayerSymbol = 'X' | 'O';

type GridSize = number;

interface GridSizeOption {
  label: string;
  value: GridSize;
}

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    PanelModule,
    CardModule,
    Button,
    NgClass,
    SelectButtonModule,
    FormsModule,
    InputNumberModule,
    ReactiveFormsModule,
    InputTextModule,
    NgIf
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

  form: FormGroup

  constructor(
    private fb: FormBuilder,
    private gameService: GameService,
    private _router: Router,
    private _messageService: MessageService) {

    this.form = this.fb.group({
      gridSize: [3 as GridSize, Validators.required],
      winCondition: [3, [Validators.required, Validators.min(3)]],
      player: this.fb.group({
        symbol: ['X' as PlayerSymbol, Validators.required]
      })
    });
  }

  playerSymbolOptions: PlayerSymbol[] = ['X', 'O'];

  gridSizeOptions: GridSizeOption[] = [
    {label: '3 x 3', value: 3},
    {label: '4 x 4', value: 4},
    {label: '5 x 5', value: 5}
  ];

  defaultMinWinCondition: number = 3;

  getGridSize() {
    return this.form.get('gridSize')?.value
  }

  startGame(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const startGameDto: StartGameDto = this.form.value;
    startGameDto.player.name = "Human"

    this.gameService.start(startGameDto).subscribe({
      next: () => {
        void this._router.navigate(['/game']);
      },
      error: (err) => {
        console.error('Error :', err);
        this._messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: "Unable to start a game. Please retry later."
        });
      }
    });
  }

  updateWinCondition(event: SelectButtonChangeEvent) {
    this.form.patchValue({gridSize: event.value});
    if (this.form.value.winCondition > event.value) {
      this.form.patchValue({winCondition: event.value});
    }
  }
}
