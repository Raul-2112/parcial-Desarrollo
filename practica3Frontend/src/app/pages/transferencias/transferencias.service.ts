import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Transferencia {
  idTransferencia: number;
  fecha: string;
  total: number;
  idCuenta: number;
}

@Injectable({
  providedIn: 'root'
})
export class TransferenciasService {
  private apiUrl = 'http://localhost:8080/transferencias';

  constructor(private http: HttpClient) {}

  listarPorUsuario(idCuenta: number): Observable<Transferencia[]> {
    return this.http.get<Transferencia[]>(`${this.apiUrl}/usuario/${idCuenta}`);
  }
}
