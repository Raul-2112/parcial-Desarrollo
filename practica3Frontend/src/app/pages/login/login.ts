import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class LoginComponent {
  correo: string = '';
  clave: string = '';
  loginError: boolean = false;

  constructor(private loginService: LoginService, private router: Router) {}

  login() {
    this.loginError = false;
    
    this.loginService.login(this.correo, this.clave).subscribe({
      next: (response) => {
        console.log('Respuesta del servidor:', response);
        
        if (response.usuario && response.cuenta) {
          this.loginService.guardarUsuario(response);
          this.router.navigate(['/transferencias']);
        } else {
          this.loginError = true;
          console.warn('Usuario o cuenta nula:', response);
        }
      },
      error: (error) => {
        console.error('Error al iniciar sesión:', error);
        this.loginError = true;
      }
    });
  }

  irARegistro() {
    this.router.navigate(['/registro']);
  }
}