import {Player} from './player';

export interface Cell {
  row: number,
  column: number,
  player: Player | null
}
