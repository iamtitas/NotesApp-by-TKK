import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  formData = {
    username: '',
    password: '',
  };
  isLoggedIn = false;

  loginError: string | null = null;

  constructor(private router: Router, private http: HttpClient) {}

  login() {
    const credentials = {
      username: this.formData.username,
      password: this.formData.password,
    };

    this.http
      .post<any>('http://localhost:8088/api/login', credentials)
      .subscribe(
        (response) => {
          this.isLoggedIn = true;
          sessionStorage.setItem('username', this.formData.username);
          this.router.navigate(['/home']);
          this.loginError = null;
        },
        (error) => {
          console.error('Login failed:', error);
          this.loginError = 'Wrong username or password. Please try again.';
        }
      );
  }
}
