import { Component } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { ambiente } from 'src/app/ambiente/ambiente';
import { HttpService } from 'src/app/service/http/http.service';

@Component({
  selector: 'app-ver-reportes',
  templateUrl: './ver-reportes.component.html',
  styleUrls: ['./ver-reportes.component.css']
})
export class VerReportesComponent {
  unidades: any;
  readonly url = ambiente.endpoint;

  constructor(private http: HttpService) { }
  ngOnInit() {
    this.getUnidades();
  }
  private getUnidades() {
  this.http.realizarGet(this.url.allunidades).subscribe((data: any) => {
    this.unidades = data.data;
    console.log(this.unidades);
  },
  (error: any) => {
    console.error('Error:', error);
  });
}
}
