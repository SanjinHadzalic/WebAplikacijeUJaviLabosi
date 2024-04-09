import { Component, OnInit } from '@angular/core';
import { Vozilo } from '../../interfaces/vozilo';
import { VoziloService } from '../../services/vozilo.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vozilo',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vozilo.component.html',
  styleUrl: './vozilo.component.css',
  providers: [VoziloService]
})
export class VoziloComponent implements OnInit{
  vozila: Vozilo[] = [];
  // selectedVozilo: Vozilo | null = null;
  newVozilo!: Vozilo;

  constructor(private voziloService: VoziloService, private router: Router) {}

  ngOnInit() : void {
    this.getVozila();
    this.newVozilo = {
      id: 3, // or any default value
      registration: '',
      vin: '',
      maxNumberOfPassenger: 0, // or any default value
      shifter: 'normal',
      airConditioning: '',
      numberOfDoors: 0, // or any default value
      fuelType: '',
      lastServiceDate: new Date(2023,2,2),
      nextServiceDate: new Date(2024,2,2),
      mileage: 24534
    };
  }

  private getVozila() {
    this.voziloService.getVoziloList().subscribe(vozilo => {
      this.vozila = vozilo;
    })
  }

  // onVoziloSelected(vozilo: Vozilo) {
  //   this.selectedVozilo = vozilo;
  // }

  returnToHome() {
    this.router.navigate([''])
  }

  onVoziloClick(id: number) {
    this.router.navigate(['vozilo', id])
  }

  saveVozilo() {
    this.voziloService.createVozilo(this.newVozilo).subscribe({
      next: (data) => {
        console.log(data)
        location.reload();
      },
      error: (e) => {
        console.log('error:',e)
      }
    })
  }

  onSubmit() {
    console.log(this.newVozilo)
    this.saveVozilo();
  }

  deleteVozilo(id:number) {
    console.log('test')
    this.voziloService.deleteVozilo(id).subscribe(data => {
      console.log(data)
      this.getVozila();
    })
  }
}
