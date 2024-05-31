import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from '../../services/notification-service.service';
import { JwtDecoderService } from '../../services/jwt-decoder.service';
import { TranslateModule } from '@ngx-translate/core';
import { FormsModule } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [TranslateModule, FormsModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  decodedToken: any;
  username!: string;
  selectedLanguage = 'hr';

  constructor(private router: Router, 
    private notificationService: NotificationService, 
    private decoderService: JwtDecoderService, 
    private translateService: TranslateService) {
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

  onLanguageChange() {
    this.translateService.use(this.selectedLanguage)
  }
}
