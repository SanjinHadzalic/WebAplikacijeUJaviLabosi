import { Injectable } from '@angular/core';

import { Vozilo } from '../interfaces/vozilo';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VoziloService {

  constructor() { }

  getVozilo(): Observable<Vozilo[]> {
    const mockVozilo: Vozilo[] = [
      {
        registration: "ZG-1234-AB",
        vin: "WBABW33426PX70804",   
        maxNumberOfPassenger: 5,
        shifter: "good one",
        airConditioning: "the best",
        numberOfDoors: 5,
        fuelType: "electric",
        newCar: true
      },
      {
        registration: "RI-4321-KC",
        vin: "WAUZZZ4BZWN049807",   
        maxNumberOfPassenger: 2,
        shifter: "barely usable",
        airConditioning: "basic A/C",
        numberOfDoors: 3,
        fuelType: "gasoline",
        newCar: false
      },
      {
        registration: "ST-1950-TO",
        vin: "2DJRN5DG9ARP44305",   
        maxNumberOfPassenger: 2,
        shifter: "barely usable",
        airConditioning: "none",
        numberOfDoors: 3,
        fuelType: "diezel",
        newCar: false
      }
    ]

    return of(mockVozilo);
  }
}
