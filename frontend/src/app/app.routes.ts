import {Routes} from '@angular/router';
import {MenuComponent} from './core/components/menu/menu.component';
import {GameComponent} from './features/components/game/game.component';
import {MainComponent} from './shared/components/layout/main/main.component';
import {gameGuard} from './shared/guards/game.guard';

export const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        redirectTo: 'menu',
        pathMatch: 'full',
      },
      {
        path: 'menu',
        component: MenuComponent,
      },
      {
        path: 'game',
        component: GameComponent,
        canActivate: [gameGuard]
      }
    ]
  }
]
