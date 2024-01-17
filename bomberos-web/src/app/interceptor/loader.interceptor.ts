// import { Injectable } from '@angular/core';
// import {
//   HttpEvent,
//   HttpInterceptor,
//   HttpHandler,
//   HttpRequest,
// } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { finalize } from 'rxjs/operators';
// import { LoaderService } from '../service/loader/loader.service';

// @Injectable()
// export class LoaderInterceptor implements HttpInterceptor {
  
//   constructor(private loader: LoaderService) {}
//   intercept(
//     req: HttpRequest<any>,
//     next: HttpHandler
//   ): Observable<HttpEvent<any>> {
//     this.loaderService.showLoader();
//     return next.handle(request).pipe(
//       finalize(() => this.loaderService.hideLoader())
//     );
//   }
// }