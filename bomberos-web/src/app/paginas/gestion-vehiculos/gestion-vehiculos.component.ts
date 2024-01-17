import { Component, OnInit } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { ambiente } from 'src/app/ambiente/ambiente';
import { HttpService } from 'src/app/service/http/http.service';
import { LoaderService } from 'src/app/service/loader/loader.service';

@Component({
  selector: 'app-gestion-vehiculos',
  templateUrl: './gestion-vehiculos.component.html',
  styleUrls: ['./gestion-vehiculos.component.css']
})
export class GestionVehiculosComponent implements OnInit {
  step = 1;
  unidades: any;
  readonly url = ambiente.endpoint;

  constructor(private http: HttpService) {
   }
  ngOnInit() {
    this.getUnidades();
  }
  private getUnidades() {
    console.log(this.url);
    this.http.realizarGet(this.url.allunidades).subscribe((data: any) => {
      this.unidades = data.data;
      console.log(this.unidades);
    },
    (error: any) => {
      console.error('Error:', error);
    });
  }

  eliminarUnidad(id: any) {
    this.http.realizarGet(`${this.url.deleteunidad}id=${id}`).subscribe((data: any) => {
      this.unidades = data.data;
      console.log(this.unidades);
    },
    (error: any) => {
      console.error('Error:', error);
    });
  }
}
