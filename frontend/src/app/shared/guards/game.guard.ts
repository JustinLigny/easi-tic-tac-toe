import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {GameService} from '../services/game.service';
import {catchError, map, of} from 'rxjs';

export const gameGuard: CanActivateFn = () => {
  const gameService = inject(GameService);
  const router = inject(Router);

  return gameService.getCurrentGame().pipe(
    map(game => {
      return game && game.board ? true : router.createUrlTree(['/menu']);
    }),
    catchError(() => of(router.createUrlTree(['/menu'])))
  );
};
