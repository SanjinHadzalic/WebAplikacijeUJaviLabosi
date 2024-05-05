import { Component, OnInit } from '@angular/core';
import { Vozilo } from '../../interfaces/vozilo';
import { VoziloService } from '../../services/vozilo.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { regExpEscape } from '@ng-bootstrap/ng-bootstrap/util/util';

@Component({
  selector: 'app-vozilo',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './vozilo.component.html',
  styleUrl: './vozilo.component.css',
  providers: [VoziloService]
})
export class VoziloComponent implements OnInit{
  vozila: Vozilo[] = [];
  // selectedVozilo: Vozilo | null = null;
  newVozilo!: Vozilo;

  constructor(private voziloService: VoziloService, private router: Router) {}

  voziloForm = new FormGroup({
    registration: new FormControl("", [
      Validators.required,
      Validators.pattern(/^[A-Z0-9]+ [-] [A-Z0-9]+$/)
    ]),
    vin: new FormControl("", [
      Validators.required,
      Validators.pattern(/^[a-zA-Z0-9]+$/)
    ]),
    maxNumberOfPassenger: new FormControl("", [
      Validators.required,
      Validators.max(6)
    ]),
    shifter: new FormControl("", [
      Validators.required
    ]),
    airConditioning: new FormControl("", [
      Validators.required
    ]),
    numberOfDoors: new FormControl("", [
      Validators.required,
      Validators.max(5)
    ]),
    fuelType: new FormControl("", [
      Validators.required
    ]),
    lastServiceDate: new FormControl("", [
      Validators.required
    ]),
    nextServiceDate: new FormControl("", [
      Validators.required
    ]),
    mileage: new FormControl("", [
      Validators.required,
      Validators.min(0)
    ]) 
  })

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

  toNextServiceDate() {
    this.router.navigate(['next'])
  }

  onVoziloClick(id: number) {
    this.router.navigate(['vozilo', id])
  }

  saveVozilo() {
    const formValue = this.voziloForm.value;
  
    this.newVozilo.registration = formValue.registration!;
    this.newVozilo.vin = formValue.vin!;
    this.newVozilo.maxNumberOfPassenger = +formValue.maxNumberOfPassenger!;
    this.newVozilo.shifter = formValue.shifter!;
    this.newVozilo.airConditioning = formValue.airConditioning!;
    this.newVozilo.numberOfDoors = +formValue.numberOfDoors!;
    this.newVozilo.fuelType = formValue.fuelType!;
    this.newVozilo.lastServiceDate = new Date(formValue.lastServiceDate!);
    this.newVozilo.nextServiceDate = new Date(formValue.nextServiceDate!);
    
    this.newVozilo.mileage = +formValue.mileage!;
  
    this.voziloService.createVozilo(this.newVozilo).subscribe({
      next: (data) => {
        console.log(data);
        location.reload(); 
      },
      error: (e) => {
        console.log('error:', e);
      }
    });
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
