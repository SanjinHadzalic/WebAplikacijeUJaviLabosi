import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Vozilo } from '../../interfaces/vozilo';
import { Review } from '../../interfaces/review';
import { VoziloService } from '../../services/vozilo.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
@Component({
  selector: 'app-vozilo-details',
  standalone: true,
  imports: [CommonModule, TranslateModule],
  templateUrl: './vozilo-details.component.html',
  styleUrl: './vozilo-details.component.css'
})
export class VoziloDetailsComponent {
  id!: number;
  vozilo!: Vozilo;
  registration!: string;
  reviews: Review[] = [];

  constructor(
    private voziloService: VoziloService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.voziloService.getVoziloById(this.route.snapshot.params['id']).subscribe((vozilo) => {
      this.vozilo = vozilo
      this.registration = this.vozilo.registration;
      this.loadReviewsForVozilo(this.registration);
    })
  }

  loadReviewsForVozilo(id: string) {
    this.voziloService.getReviewsByVoziloRegistration(this.registration).subscribe((reviews) => {
      this.reviews = reviews;
    });
  }

  return() {
    this.router.navigate(['/vozilo'])
  }
}
