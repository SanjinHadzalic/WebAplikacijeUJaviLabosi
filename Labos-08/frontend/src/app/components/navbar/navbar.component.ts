import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from '../../services/notification-service.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private router: Router, private notificationService: NotificationService) {}

  logoutUser():void {
    localStorage.removeItem('JWT');
    this.notificationService.logoutMessageSuccess('Logged out', 'You have been successfully logged out!')
    this.router.navigate(['/login'])
  }
}
