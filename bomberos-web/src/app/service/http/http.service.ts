import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoaderService } from '../loader/loader.service';
import { catchError, tap } from 'rxjs';
import { Url } from 'src/app/url';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient, private loaderService: LoaderService) { }

  private requestActivas = 0;

  public realizarGet(url: string, omitirLoader?: boolean, opciones?: any): any {
    if (!omitirLoader) {
      this.requestActivas += 1;
      this.loaderService.show();
    }
    return this.http.get(url, opciones).pipe( 
      tap(_ => {
        if (!omitirLoader) {
          this.requestActivas -= 1;
        }
        if (this.requestActivas == 0) {
          this.loaderService.hide();
        }
      }),
      catchError((error: HttpErrorResponse) => {
        if (!omitirLoader) {
          this.requestActivas -= 1;
        }
        if (this.requestActivas == 0) {
          this.loaderService.hide();
        }
        return 'error';
      })
    );
  }
  httpOptionGet = {
    withCredentials: true
  }
  httpOptions = {
    headers: {
      'Content-Type': 'application/json'
    },
    withCredentials: true // Include this option to send cookies
  };
  public realizarPost(url: string, datos: any, omitirLoader?: boolean, opciones?: any): any {
    if (!omitirLoader) {
      this.loaderService.show();
    }
    return this.http.post(url, datos, opciones).pipe(
      tap(_ => {
        this.loaderService.hide();
      }),
      catchError((error: HttpErrorResponse) => {
        this.loaderService.hide();

        return 'error';
      })
    );
  }
  public realizarPut(url: string, datos?: any, omitirLoader?: boolean, opciones?: any): any {
    if (!omitirLoader) {
      this.loaderService.show();
    }
    return this.http.put(url, datos, opciones).pipe(
      tap(_ => {
        this.loaderService.hide();
      }),
      catchError((error: HttpErrorResponse) => {
        this.loaderService.hide();
        return 'error';
      })
    );
  }

  public realizarDelete(url: string, datos?: any, omitirLoader?: boolean, opciones?: any): any {
    if (!omitirLoader) {
      this.loaderService.show();
    }
    return this.http.delete(Url.urlJsonSv + url, datos).pipe(
      tap(_ => {
        this.loaderService.hide();

      }),
      catchError((error: HttpErrorResponse) => {
        this.loaderService.hide();

        return 'error';
      })
    );
  }

}
