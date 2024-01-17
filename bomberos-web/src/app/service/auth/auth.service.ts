import { Injectable } from '@angular/core';
import { map, catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { HttpService } from '../http/http.service';
import { ambiente } from 'src/app/ambiente/ambiente';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loggedInStatus = false;
  userDetails : any; // TODO - Refactor a clase especifica

  readonly url = ambiente.endpoint;
  constructor(private httpService: HttpService) { }
  
  setLogeado(value : boolean){
    this.loggedInStatus = value;
    console.log()
  }

  setUserDetails(value: any){
    this.userDetails = value;
  }

  get isLogged(){
    return this.loggedInStatus;
  }

  get getUserDetails(){
    return this.userDetails;
  }
  
  isUserLogged() {
    return this.httpService.realizarGet(this.url.info, true).pipe(
      map((respuesta: any) => {
        if (respuesta['state'] === "OK") {
          this.setLogeado(true);
          this.setUserDetails(respuesta['data']);
          return true;
        } else {
          // Si no es "OK", también debes devolver un valor
          this.setLogeado(false);
          this.setUserDetails(null);
          return false;
        }
      }),
      catchError((err) => {
        if (this.loggedInStatus) {
          // this.modalService.mostrarError(AppSettings.MENSAJE_SESION_EXPIRADA, AppSettings.TITULO_SESION_EXPIRADA).afterClosed().subscribe(() => {
          //   location.reload();
          // });
        }
        this.setLogeado(false);
        this.setUserDetails(null);
        return of(false);
      })
    );
  }

  logout(){
    this.httpService.realizarGet(this.url.logout)
      .subscribe((respuesta : any) => {
          this.setLogeado(false);
          this.setUserDetails(null);
          location.reload();
      },
        (error: any) => console.log('error', error));
  }

}
