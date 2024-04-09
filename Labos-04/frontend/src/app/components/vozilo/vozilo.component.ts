import { Component, OnInit } from '@angular/core';
import { Vozilo } from '../../interfaces/vozilo';
import { VoziloService } from '../../services/vozilo.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vozilo',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './vozilo.component.html',
  styleUrl: './vozilo.component.css',
  providers: [VoziloService]
})
export class VoziloComponent implements OnInit{
  vozila: Vozilo[] = [];
  selectedVozilo: Vozilo | null = null;

  constructor(private voziloService: VoziloService, private router: Router) {}

  ngOnInit() : void {
    this.getVozila();
  }

  private getVozila() {
    this.voziloService.getVoziloList().subscribe(vozilo => {
      this.vozila = vozilo;
    })
  }

  onVoziloSelected(vozilo: Vozilo) {
    this.selectedVozilo = vozilo;
  }

  returnToHome() {
    this.router.navigate([''])
  }

  onVoziloClick(id: number) {
    this.router.navigate(['vozilo', id])
  }
}
