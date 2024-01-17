import { Component } from '@angular/core';
import { HttpService } from 'src/app/service/http/http.service';

@Component({
  selector: 'app-crear-persona',
  templateUrl: './crear-persona.component.html',
  styleUrls: ['./crear-persona.component.css']
})
export class CrearPersonaComponent {
  nuevoUsuario = {
    nombreCompleto: '',
    password: '',
    username: ''
  }
  mostrarContrasenia = false;
  constructor(private http: HttpService){}
  
  isUserValido(){
    if (this.nuevoUsuario.nombreCompleto.trim() === '' || this.nuevoUsuario.password.trim() === '' || this.nuevoUsuario.username.trim() === ''){
      return false;
    }
    else
      return true
  }

  submitForm() {
    this.http.realizarPost('user/createuser', this.nuevoUsuario).subscribe(
      (retorno: any) => {
        if(retorno.state == 'OK')
          console.log(retorno);
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
}
