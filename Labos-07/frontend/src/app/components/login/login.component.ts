import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private authService: AuthService) { }

  username!: string;
  password!: string;

  login() {
    this.authService.login(this.username, this.password).subscribe(
      response => {
        console.log('Login successful');
      },
      error => {
        console.log('Login failed');
      }
    );
  }
}
