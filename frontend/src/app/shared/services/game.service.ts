import {Injectable} from '@angular/core';
import {API_URLS, environment} from '../../../environments/environment.development';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Game} from '../models/game';
import {StartGameDto} from '../dto/start-game-dto';
import {PlacePawnDto} from '../dto/place-pawn-dto';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private static GAME_URL = `${environment.BASE_URL}/${API_URLS.GAME}`;
  constructor(private _http: HttpClient) {
  }

  start(startGameDto: StartGameDto): Observable<Game> {
    const url = `${GameService.GAME_URL}/start`;
    return this._http.post<Game>(url, startGameDto);
  }

  restart(): Observable<Game> {
    const url = `${GameService.GAME_URL}/restart`;
    return this._http.put<Game>(url, "");
  }

  placePawn(placePawDto: PlacePawnDto): Observable<Game> {
    const url = `${GameService.GAME_URL}/place-pawn`;
    return this._http.post<Game>(url, placePawDto);
  }

  getCurrentGame(): Observable<Game> {
    return this._http.get<Game>(GameService.GAME_URL);
  }
}
