import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { VoziloComponent } from './components/vozilo/vozilo.component';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, RouterLink, RouterLinkActive, VoziloComponent, TranslateModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-i18n';
  selectedLanguage = 'hr';

  constructor(private translateService: TranslateService){}

  onLanguageChange() {
    this.translateService.use(this.selectedLanguage)
  }
}
