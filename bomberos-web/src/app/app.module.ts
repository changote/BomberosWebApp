import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './paginas/home/home.component';
import { ToolbarComponent } from './paginas/shared/toolbar/toolbar.component';
import { FooterComponent } from './paginas/shared/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './modules/material/material.module';
import { CrearReporteComponent } from './paginas/crear-reporte/crear-reporte.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularFireModule } from '@angular/fire/compat';


import { LoaderComponent } from './paginas/shared/loader/loader.component';
import { PanelAdminComponent } from './paginas/panel-admin/panel-admin.component';
import { VerReportesComponent } from './paginas/ver-reportes/ver-reportes.component';
import { GestionVehiculosComponent } from './paginas/gestion-vehiculos/gestion-vehiculos.component';
import { GestionPersonasComponent } from './paginas/gestion-personas/gestion-personas.component';
import { HttpClientModule } from '@angular/common/http';
import { CrearVehiculoComponent } from './paginas/crear-vehiculo/crear-vehiculo.component';
import { ListaReporteComponent } from './paginas/lista-reporte/lista-reporte.component';
import { LoaderService } from './service/loader/loader.service';
import { CrearPersonaComponent } from './paginas/crear-persona/crear-persona.component';
import { LoginComponent } from './modales/login/login.component';

registerLocaleData(localeEs);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ToolbarComponent,
    FooterComponent,
    CrearReporteComponent,
    LoaderComponent,
    PanelAdminComponent,
    VerReportesComponent,
    GestionVehiculosComponent,
    GestionPersonasComponent,
    CrearVehiculoComponent,
    ListaReporteComponent,
    CrearPersonaComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    LoaderService,
    { provide: LOCALE_ID, useValue: 'es' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
