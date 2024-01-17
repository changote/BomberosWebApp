import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './paginas/home/home.component';
import { CrearReporteComponent } from './paginas/crear-reporte/crear-reporte.component';
import { PanelAdminComponent } from './paginas/panel-admin/panel-admin.component';
import { VerReportesComponent } from './paginas/ver-reportes/ver-reportes.component';
import { GestionVehiculosComponent } from './paginas/gestion-vehiculos/gestion-vehiculos.component';
import { GestionPersonasComponent } from './paginas/gestion-personas/gestion-personas.component';
import { CrearVehiculoComponent } from './paginas/crear-vehiculo/crear-vehiculo.component';
import { ListaReporteComponent } from './paginas/lista-reporte/lista-reporte.component';
import { CrearPersonaComponent } from './paginas/crear-persona/crear-persona.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'crear-reporte', component: CrearReporteComponent },
  { path: 'panel-admin', component: PanelAdminComponent},
  { path: 'ver-reportes', component: VerReportesComponent },
  { path: 'gestion-vehiculos', component: GestionVehiculosComponent},
  { path: 'crear-vehiculo', component: CrearVehiculoComponent },
  { path: 'gestion-personas', component: GestionPersonasComponent },
  { path: 'ver-reportes/:id', component: ListaReporteComponent },
  { path: 'crear-usuario', component: CrearPersonaComponent },
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

