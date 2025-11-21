import { Injectable, signal } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface UsuarioResponse {
  idUsuario: number;
  nombre: string;
  apellido: string;
  documento: string;
}

export interface CuentaResponse {
  idCuenta: number;
  numeroCuenta: string;
  saldo: number;
  usuario: UsuarioResponse;
}

export interface LoginResponse {
  mensaje: string;
  usuario: UsuarioResponse;
  cuenta: CuentaResponse | null;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  usuarioLogueado = signal<LoginResponse | null>(null);

  private apiUrl = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {}

  login(correo: string, clave: string): Observable<LoginResponse> {
    const body = { correo, clave };
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, body, { 
      headers,
      responseType: 'json'
    });
  }

  logout() {
    localStorage.removeItem('usuario');
    this.usuarioLogueado.set(null);
  }

  guardarUsuario(usuario: LoginResponse) {
    this.usuarioLogueado.set(usuario);
    localStorage.setItem('usuario', JSON.stringify(usuario));
  }
}