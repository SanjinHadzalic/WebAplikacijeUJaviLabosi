import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NotificationService } from '../../services/notification-service.service';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  providers: [AuthService]
})
export class LoginComponent implements OnInit{
  loginForm!: FormGroup;
  errorMessage!: string;

  constructor(
    private authService: AuthService, 
    private formBuilder: FormBuilder,
    private router: Router,
    private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }


  onSubmit() {
    const username = this.loginForm.value.username;
    const password = this.loginForm.value.password;

    this.authService.login(username, password).subscribe((response) => {
      if(response.accessToken){
        // alert(response.accessToken)
        this.notificationService.authentificationMessageSuccess("Logged in!", "Successfully logged in redirecting to home...")
        const jwtToken = response.accessToken
        localStorage.setItem('JWT', jwtToken)
        this.router.navigate(['/'])
      } 
    })
  }

}
