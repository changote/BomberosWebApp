import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ambiente } from 'src/app/ambiente/ambiente';
import { HttpService } from 'src/app/service/http/http.service';

@Component({
  selector: 'app-crear-vehiculo',
  templateUrl: './crear-vehiculo.component.html',
  styleUrls: ['./crear-vehiculo.component.css']
})
export class CrearVehiculoComponent implements OnInit {
  laterales: { nombre: string, isLateral: boolean }[] = [];
  motores: { nombre: string, isLateral: boolean }[] = [];
  isOk = false;
  nuevoVehiculo: any = {};
  nombreUnidad: any;
  nombreLateral: any;
  nombreMotor: any;
  readonly url = ambiente.endpoint;

  crearLateral(index: number): void {
    this.laterales.push({ nombre: this.laterales[index].nombre, isLateral: true });
    this.laterales[index].nombre = '';
  }

  eliminarLateral(index: number): void {
    if (index >= 0 && index < this.laterales.length) {
      this.laterales.splice(index, 1);
    }
  }
  eliminarMotor(index: number): void {
    if (index >= 0 && index < this.motores.length) {
      this.motores.splice(index, 1);
    }
  }
  crearLateralNuevo(): void {
    this.laterales.push({ nombre: this.nombreLateral, isLateral: true });
    this.nombreLateral = undefined;
    console.log(this.laterales);
  }
  crearMotor(index: number): void {
    this.motores.push({ nombre: this.motores[index].nombre, isLateral: false });
    this.motores[index].nombre = '';
  }
  crearMotorNuevo(): void {
    this.motores.push({ nombre: this.nombreMotor, isLateral: false });
    this.nombreMotor = undefined;
    console.log(this.motores);
  }

  constructor(private router: Router, private http: HttpService) { }

  ngOnInit() {
  }
  nuevaUnidad() {
    this.nuevoVehiculo.parteListPost = [...this.laterales, ...this.motores];
    this.nuevoVehiculo.nombre = this.nombreUnidad;

    this.http.realizarPost(this.url.nueva_unidad, this.nuevoVehiculo).subscribe(
      (retorno: any) => {
        console.log(retorno);
        if (retorno.state == 'OK') {
          this.isOk;
          this.router.navigate(['/gestion-vehiculos']);
        }

      },
      (error: any) => {
        console.error(error);
      }
    );
  }
  submitForm(): void {
    console.log(this.nuevoVehiculo)
    this.nuevaUnidad();
  }


}
