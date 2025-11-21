import { Component, inject } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

interface UsuarioResponse {
  idUsuario: number;
  nombre: string;
  apellido: string;
  documento: string;
}

interface CuentaResponse {
  idCuenta: number;
  numeroCuenta: string;
  saldo: number;
  usuario: UsuarioResponse;
}

@Component({
  selector: 'app-registro',
  standalone: true,
  templateUrl: './registro.html',
  styleUrl: './registro.css',
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    HttpClientModule
  ]
})
export class RegistroComponent {
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);
  private router = inject(Router);

  registroForm = this.fb.group({
    nombre: ['', Validators.required],
    apellido: ['', Validators.required],
    documento: ['', [Validators.required, Validators.minLength(6)]],
    telefono: ['', [Validators.required, Validators.minLength(7)]],
    correo: ['', [Validators.required, Validators.email]],
    contraseña: ['', [Validators.required, Validators.minLength(6)]],
  });

  registrando = false;
  errorMensaje = '';
  cuentaCreada: CuentaResponse | null = null;

  registrarUsuario() {
    if (this.registroForm.invalid) {
      this.errorMensaje = 'Por favor completa todos los campos correctamente.';
      return;
    }

    this.registrando = true;
    this.errorMensaje = '';

    const body = this.registroForm.value;

    this.http.post<CuentaResponse>('http://localhost:8080/usuarios/registro', body).subscribe({
      next: (response) => {
        this.cuentaCreada = response;
        this.registrando = false;
      },
      error: (err) => {
        console.error('Error en el registro:', err);
        this.errorMensaje = 'Error al registrar el usuario. Inténtalo de nuevo.';
        this.registrando = false;
      }
    });
  }

  volverLogin() {
    this.router.navigate(['/login']);
  }
}
