import { Component, OnInit } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { ambiente } from 'src/app/ambiente/ambiente';
import { HttpService } from 'src/app/service/http/http.service';

@Component({
  selector: 'app-gestion-personas',
  templateUrl: './gestion-personas.component.html',
  styleUrls: ['./gestion-personas.component.css']
})
export class GestionPersonasComponent implements OnInit {
  usuarios: any;
  readonly url = ambiente.endpoint;

  constructor(private http: HttpService) { }

  ngOnInit(): void {
    this.getUsers();
  }
  private async getUsers() {
    try {
      let responseApi = this.http.realizarGet(this.url.getusers);
      const data = await lastValueFrom(responseApi);
      this.usuarios = data;
      console.log(this.usuarios);
      this.usuarios = this.usuarios.data.sort((a: { bloqueado: any; }, b: { bloqueado: any; }) => {
        // Si 'a' está bloqueado y 'b' no, 'a' tiene una prioridad menor
        if (a.bloqueado && !b.bloqueado) {
          return 1;
        }
        // Si 'b' está bloqueado y 'a' no, 'b' tiene una prioridad menor
        if (!a.bloqueado && b.bloqueado) {
          return -1;
        }
        return 0
      });
      console.log(this.usuarios);
    } catch (error) {
      console.error('Error:', error);
    }
  }

  bajaUser(id: number) {
    this.http.realizarPut(`${this.url.bajauser}id=${id}`).subscribe(
      (retorno: any) => {
        console.log(retorno);
        if (retorno.state == 'OK') {
          this.actualizarUser(retorno.data);
          console.log(this.usuarios)
        }
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  private actualizarUser(userActualizado: any) {
    const usuariosData = this.usuarios;

    for (let i = 0; i < usuariosData.length; i++) {
      if (usuariosData[i].userId === userActualizado.userId) {
        usuariosData[i] = userActualizado;
        break; // No es necesario seguir recorriendo una vez que se encuentra el usuario
      }
    }
  }
}
