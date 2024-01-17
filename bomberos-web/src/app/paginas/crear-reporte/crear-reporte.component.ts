import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnimationItem } from 'lottie-web';
import { AnimationOptions } from 'ngx-lottie';
import { lastValueFrom } from 'rxjs';
import { AnyCatcher } from 'rxjs/internal/AnyCatcher';
import { ambiente } from 'src/app/ambiente/ambiente';
import { Registro } from 'src/app/entity/model-implements';
import { HttpService } from 'src/app/service/http/http.service';
import { LoaderService } from 'src/app/service/loader/loader.service';

@Component({
  selector: 'app-crear-reporte',
  templateUrl: './crear-reporte.component.html',
  styleUrls: ['./crear-reporte.component.css']
})
export class CrearReporteComponent implements OnInit {
  isOk:boolean = false;
  formValid = false;
  unidades: any;
  options: AnimationOptions = {
    path: '../../../assets/img/icons8-check.json',
    loop: false,
  };
  registro: Registro = new Registro();
  unidadElegida: any;
  unidadCompleta: any;
  step = 1;
  readonly url = ambiente.endpoint;

  switchStep(newStep: number) {
    this.step = newStep;
    this.resetRegistro();
  }

  constructor(private http: HttpService, private router: Router) {
  }

  onSubmit() {
    this.step = 2;
    this.getUnidadCompletaByUnidadId();
  }

  ngOnInit() {
    this.getUnidades();
    console.log(this.registro)
  }


  isFormValid() {
    this.formValid = this.registro.user != '' && this.registro.idUnidad != undefined;
  }
  asignarIdParte(id:any, i:any, isLateral:boolean){
    if(isLateral)
      this.registro.lateral.parteList[i].idParte = id;
    else
    this.registro.motor.parteList[i].idParte = id;
  }

  formValid2() {
    for (let parte of this.registro.lateral.parteList) {
      if (parte.isOk == undefined)
        return false;
    }
    for (let parte of this.registro.motor.parteList) {
      if (parte.isOk == undefined)
        return false;
    }
    return true;
  }

  resetRegistro() {
    this.registro = new Registro();
  }

  sendRegistro() {
    let registroToSend: Omit<Registro, 'motor'> = this.registro;
  
    if (this.registro.motor.parteList.length === 0) {
      // Create a new object without the motor property
      const { motor, ...registroWithoutMotor } = this.registro;
      registroToSend = registroWithoutMotor;
    }
    this.http.realizarPost(this.url.nuevoregistro, registroToSend).subscribe(
      (retorno: any) => {
        console.log(retorno);
        if(retorno.state == 'OK')
          this.isOk = true;
        this.step = 3;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
  redireccionar(){
    this.router.navigate(['/otra-ruta']);
  }

  private getUnidades() {
    this.http.realizarGet(this.url.allunidades).subscribe(
      (retorno: any) => {
        this.unidades = retorno.data;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  public async getUnidadCompletaByUnidadId() {
    try {
      let responseApi = this.http.realizarGet(`${this.url.unidadcompletabyid}id=${this.registro.idUnidad}`);
      let data = await lastValueFrom(responseApi) as any;
      data = data.data;
      this.unidadCompleta = {
        lateral: [],
        motor: [],
      };

      const laterales = data.parteList.filter((parte: any) => parte.lateral === true);
      const noLaterales = data.parteList.filter((parte: any) => parte.lateral === false);

      // Assign values to the properties of this.unidadCompleta
      this.unidadCompleta.lateral.push(...laterales);
      this.unidadCompleta.motor.push(...noLaterales);
      this.unidadCompleta.nombre = data.nombre;
      this.unidadCompleta.idUnidad = data.idUnidad;
      this.unidadElegida = this.unidadCompleta.nombre;
      console.log(this.registro)
      console.log(this.unidadCompleta)
      this.unidadToRegistro();

    } catch (error) {
      console.error('Error:', error);
    }
  }

  private unidadToRegistro() {
    this.registro.lateral.parteList = [];
    const part = { idParte: 0, isOk: false };

    for (let parte of this.unidadCompleta.lateral) {
      this.registro.lateral.parteList.push(Object.assign({}, part));
    }
    for (let parte of this.unidadCompleta.motor) {
      this.registro.motor.parteList.push(Object.assign({}, part));
    }
    console.log(this.registro)
  }

}


