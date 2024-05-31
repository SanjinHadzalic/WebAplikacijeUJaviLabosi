import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from '../../services/notification-service.service';
import { JwtDecoderService } from '../../services/jwt-decoder.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  decodedToken: any;
  username!: string;


  constructor(private router: Router, private notificationService: NotificationService, private decoderService: JwtDecoderService) {
    this.username = this.getUsername()

  }


  getUsername():string {
    const jwtToken = localStorage.getItem('JWT');
    this.decodedToken = this.decoderService.decodeToken(jwtToken ?? "qwe")


    return this.decodedToken.sub;
  }

  logoutUser():void {
    localStorage.removeItem('JWT');
    this.notificationService.logoutMessageSuccess('Logged out', 'You have been successfully logged out!')
    this.router.navigate(['/login'])
  }
}
