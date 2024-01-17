import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ambiente } from 'src/app/ambiente/ambiente';
import { AuthService } from 'src/app/service/auth/auth.service';
import { HttpService } from 'src/app/service/http/http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = {
    password: '',
    username: ''
  }
  mostrarContrasenia = false;
  readonly url = ambiente.endpoint;
  constructor(private http: HttpService, private router: Router, private authService: AuthService) { }

  isUserValido() {
    if (this.user.password.trim() === '' || this.user.username.trim() === '') {
      return false;
    }
    else
      return true
  }

  submitForm() {
    this.http.realizarPost(this.url.login, this.user).subscribe(
      (response: any) => {
        localStorage.clear();
        this.authService.setLogeado(true);
        location.reload();
        this.router.navigate(['/']);
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
}
