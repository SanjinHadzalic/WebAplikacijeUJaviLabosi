import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Vozilo } from '../../interfaces/vozilo';
import { VoziloService } from '../../services/vozilo.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-vozilo-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './vozilo-details.component.html',
  styleUrl: './vozilo-details.component.css'
})
export class VoziloDetailsComponent {
  id!: number;
  vozilo!: Vozilo;

  constructor(
    private voziloService: VoziloService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.voziloService.getVoziloById(this.route.snapshot.params['id']).subscribe((vozilo) => {
      this.vozilo = vozilo
    })
  }

  return() {
    this.router.navigate(['/vozilo'])
  }
}
