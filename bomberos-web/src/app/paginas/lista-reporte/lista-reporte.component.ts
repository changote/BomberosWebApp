import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { ambiente } from 'src/app/ambiente/ambiente';
import { HttpService } from 'src/app/service/http/http.service';

@Component({
  selector: 'app-lista-reporte',
  templateUrl: './lista-reporte.component.html',
  styleUrls: ['./lista-reporte.component.css']
})
export class ListaReporteComponent implements AfterViewInit {
  registros: any;
  id: any;
  displayedColumns: string[] = ['fecha', 'usuario', 'lateral', 'motor'];
  dataSource = new MatTableDataSource<any>([]);
  readonly url = ambiente.endpoint;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  nombreUnidad: any;
  
  constructor(private http: HttpService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });
    this.getNombreUnidad();
    this.getRegistros();
  }
  private async getRegistros() {
    try {
      let responseApi = this.http.realizarGet(`${this.url.allregistros}id=${this.id}`);
      const data = await lastValueFrom(responseApi);
      this.registros = data;
      this.registros = this.registros.data;
      this.dataSource = new MatTableDataSource<any>(this.registros);
      this.dataSource.paginator = this.paginator;
    } catch (error) {
      console.error('Error:', error);
    }
  }
  private async getNombreUnidad() {
    try {
      let responseApi = this.http.realizarGet(`${this.url.unidadbyid}id=${this.id}`);
      const response = await lastValueFrom(responseApi);
      this.nombreUnidad = response;
    } catch (error) {
      console.error('Error:', error);
    }
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
