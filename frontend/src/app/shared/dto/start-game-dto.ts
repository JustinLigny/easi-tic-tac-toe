import {CreatePlayerDto} from './create-player-dto';

export interface StartGameDto {
  gridSize: number,
  winCondition: number,
  player: CreatePlayerDto
}
