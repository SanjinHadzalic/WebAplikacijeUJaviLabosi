import { Component, OnInit } from '@angular/core';
import { Vozilo } from '../../interfaces/vozilo';
import { VoziloNew } from '../../interfaces/vozilo-new';
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
  vozila: Vozilo[]  = [];
  selectedVozilo: Vozilo | null = null;

  constructor(private voziloService: VoziloService, private router: Router) {}

  ngOnInit() : void {
    this.getVozila();
    this.router.navigate([''])
  }

  private getVozila() {
    this.voziloService.getVozilo().subscribe(vozilo => {
      this.vozila = vozilo;
    })
  }

  onVoziloSelected(vozilo: Vozilo) {
    if(vozilo.registration.startsWith('ZG') || vozilo.registration.startsWith('RI')) {
      this.selectedVozilo = vozilo;
    } else {
      this.selectedVozilo
    }
  }

  public sortVoziloDesc(): void {
    this.vozila = this.vozila.sort((a,b) => a.registration.localeCompare(b.registration))
  }

  public sortVoziloAsc() : void {
    this.vozila = this.vozila.sort((a,b) => b.registration.localeCompare(a.registration))
  }

  filterBy(brandInput: HTMLInputElement) {
    if(brandInput.value) {
      this.vozila = this.vozila.filter(q => q.vin.startsWith(brandInput.value))
    } 
    this.router.navigate([''])
  }
}
