import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {Button} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {InputNumberModule} from 'primeng/inputnumber';
import {SelectButtonModule} from 'primeng/selectbutton';
import {ToastModule} from 'primeng/toast';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [
    RouterOutlet,
    Button,
    FormsModule,
    InputNumberModule,
    ReactiveFormsModule,
    SelectButtonModule,
    ToastModule
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

}
