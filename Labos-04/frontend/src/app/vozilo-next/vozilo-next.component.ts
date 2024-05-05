import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Vozilo } from 'app/interfaces/vozilo';
import { VoziloService } from 'app/services/vozilo.service';


@Component({
  selector: 'app-vozilo-next',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './vozilo-next.component.html',
  styleUrl: './vozilo-next.component.css',
  providers: [VoziloService]
})
export class VoziloNextComponent {
  vozila: Vozilo[] = [];
  next!: Vozilo[];

  constructor(private voziloService: VoziloService) {}

  ngOnInit() : void {
    this.getVozila();
  }

  private getVozila() {
    this.voziloService.getNext().subscribe(vozilo => {
      this.vozila = vozilo;
    })
  }

  
}
