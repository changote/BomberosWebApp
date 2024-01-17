import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Event,NavigationEnd, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs/internal/Observable';
import { LoginComponent } from 'src/app/modales/login/login.component';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent {
  usuarioLogueado = false;
  userDetails : any;
  constructor(private cookieService: CookieService, private router: Router, private dialog: MatDialog, private autenticationService : AuthService){
    this.router.events.subscribe((event: Event) => {
      if (event instanceof NavigationEnd) {
        var isLogged = this.autenticationService.isUserLogged();
        if(isLogged instanceof Observable){
          isLogged.subscribe(data =>{
            this.onLoginEvent();
          });
        } else
          this.onLoginEvent();
      }
    });

  }

  getUserData(){
    this.cookieService.get('user');
  }

  panelAdmin() {
    this.router.navigate(['/panel-admin']);
  }

  modalLogin(){
    this.dialog.open(LoginComponent, {
      height: 'auto',
      width: 'auto',
    });
  }

  private onLoginEvent() {
    this.usuarioLogueado = this.autenticationService.isLogged;
    this.userDetails = JSON.parse(JSON.stringify(this.autenticationService.getUserDetails));
    if (this.usuarioLogueado == true) {
      var nombres = this.userDetails.nombre.split(' ');
      this.userDetails.nombre = nombres[0];
    }
  }
  logout(){
    this.autenticationService.logout();
    this.router.navigate(['/']);
  }


}
