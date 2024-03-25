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
        maxNumberOfPassenger: 5,
        shifter: "good one",
        airConditioning: "the best",
        numberOfDoors: 5,
        fuelType: "electric",
        newCar: true
      },
      {
        maxNumberOfPassenger: 2,
        shifter: "barely usable",
        airConditioning: "A/C?",
        numberOfDoors: 3,
        fuelType: "gasoline",
        newCar: false
      }
    ]

    return of(mockVozilo);
  }
}
