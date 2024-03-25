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

  constructor(private voziloService: VoziloService, private router: Router) {}

  ngOnInit() : void {
    this.getVozila();
    this.router.navigate(['/vozila'])
  }

  private getVozila() {
    this.voziloService.getVozilo().subscribe(vozilo => {
      this.vozila = vozilo;
    })

  }

}
