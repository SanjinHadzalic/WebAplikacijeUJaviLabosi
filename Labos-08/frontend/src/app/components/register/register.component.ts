import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { UserInfo } from '../../interfaces/user';
import { CommonModule } from '@angular/common';
import { UserRole } from '../../interfaces/user-roles';
import { NotificationService } from '../../services/notification-service.service';
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{
  newUser!: UserInfo;
  newRole: UserRole = {id:1, name: 'ROLES_USER'}
  
  constructor(
    private authService: AuthService,
    private notificationService: NotificationService
  ){}
  
  registerForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(3)]),
    password: new FormControl('', [Validators.required, Validators.minLength(4)]),
    // firstName: new FormControl('', Validators.required),
    // lastName: new FormControl('', Validators.required)
  });

  ngOnInit(): void {
    this.newUser = {
      id: 4,
      username: '',
      password: '',
      roles: Array.of(this.newRole)
      // first_name: '',
      // last_name: ''
    }
  }

  onSubmit() {
    if(this.registerForm.valid) {
      const formValue = this.registerForm.value

      this.newUser.username = formValue.username!;
      this.newUser.password = formValue.password!;
      // this.newUser.first_name = formValue.firstName!;
      // this.newUser.last_name = formValue.lastName!;

      console.log(this.newUser)

      this.authService.registerUser(this.newUser).subscribe({
        next: (data) => {
          this.notificationService.successNotificationRegisterWithRedirection('Registration successful', 'Now get out of here!')
        },
        error: (e) => {
          console.log('error', e);
        }
      })
    }
  }
}
