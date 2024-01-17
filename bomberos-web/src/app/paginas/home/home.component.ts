import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/service/auth/auth.service';
import { HttpService } from 'src/app/service/http/http.service';
import { LoaderService } from 'src/app/service/loader/loader.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  navigationSubscription;
  usuarioLogueado: boolean;
  
  constructor(private loaderService: LoaderService, public route: ActivatedRoute, private router: Router, private autenticationService : AuthService) {
    this.loaderService.show();
    setTimeout(() => {
    this.loaderService.hide();
    }, 1000);
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      if(e instanceof NavigationEnd) {

        var isLogged = this.autenticationService.isUserLogged();
        if(isLogged instanceof Observable){
          isLogged.subscribe(data =>{
            this.onLoginEvent();
          });
        } else
          this.onLoginEvent();
      }
    });
    this.usuarioLogueado = this.autenticationService.isLogged;
  }

  redirectToCrearReporte() {
    this.router.navigate(['/crear-reporte']);
  }
  private onLoginEvent() {
    this.usuarioLogueado = this.autenticationService.isLogged;
  }

}