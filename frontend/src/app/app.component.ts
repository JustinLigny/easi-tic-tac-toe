import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {Button} from 'primeng/button';
import {GameComponent} from './features/components/game/game.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, Button, GameComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
}
