import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TransferenciasService, Transferencia } from './transferencias.service';
import { CommonModule } from '@angular/common';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-transferencias',
  standalone: true,
  templateUrl: './transferencias.html',
  styleUrls: ['./transferencias.css'],
  imports: [CommonModule, MatExpansionModule, MatCardModule]
})
export class TransferenciasComponent implements OnInit {
  transferencias: Transferencia[] = [];
  cargando: boolean = true;
  error: string = '';

  constructor(private service: TransferenciasService, private router: Router) {}

  ngOnInit() {
    const usuarioStr = localStorage.getItem('usuario');
    console.log('Usuario en localStorage:', usuarioStr);
    
    if (!usuarioStr) {
      this.router.navigate(['/login']);
      return;
    }

    try {
      const usuario = JSON.parse(usuarioStr);
      console.log('Usuario parseado:', usuario);
      
      const idCuenta = usuario.cuenta?.idCuenta;
      console.log('ID Cuenta:', idCuenta);
      
      if (!idCuenta) {
        this.error = 'No se encontró la cuenta del usuario';
        this.cargando = false;
        return;
      }

      this.service.listarPorUsuario(idCuenta).subscribe({
        next: (data) => {
          this.transferencias = data;
          this.cargando = false;
        },
        error: (err) => {
          console.error('Error al obtener transferencias:', err);
          this.error = 'Error al cargar las transferencias';
          this.cargando = false;
        }
      });
    } catch (e) {
      this.router.navigate(['/login']);
    }
  }
}