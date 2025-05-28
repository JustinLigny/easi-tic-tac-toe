import {Board} from './board';
import {Player} from './player';

export interface Game {
  winCondition: number,
  board: Board,
  humanPlayer: Player,
  computerPlayer: Player,
  currentPlayer: Player,
  status: string
}
