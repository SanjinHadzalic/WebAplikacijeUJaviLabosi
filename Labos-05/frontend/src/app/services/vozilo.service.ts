import { Injectable } from '@angular/core';

import { Vozilo } from '../interfaces/vozilo';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VoziloService {

  private basUrl = "http://localhost:8080/vozilo"

  constructor(private httpClient: HttpClient) { }

  getVozilo(): Observable<Vozilo[]> {
    const mockVozilo: Vozilo[] = [
      {
        id: 1,
        registration: "ZG-1234-AB",
        vin: "WBABW33426PX70804",   
        maxNumberOfPassenger: 5,
        shifter: "good one",
        airConditioning: "the best",
        numberOfDoors: 5,
        fuelType: "electric",
        lastServiceDate: new Date(2020,2,2),
        nextServiceDate: new Date(2025,2,2),
        mileage:12123
        // newCar: true
      },
      {
        id: 2,
        registration: "RI-4321-KC",
        vin: "WAUZZZ4BZWN049807",   
        maxNumberOfPassenger: 2,
        shifter: "barely usable",
        airConditioning: "basic A/C",
        numberOfDoors: 3,
        fuelType: "gasoline",
        lastServiceDate: new Date(2020,2,2),
        nextServiceDate: new Date(2025,2,2),
        mileage:12123
        // newCar: false
      },
      {
        id: 3,
        registration: "ST-1950-TO",
        vin: "2DJRN5DG9ARP44305",   
        maxNumberOfPassenger: 2,
        shifter: "barely usable",
        airConditioning: "none",
        numberOfDoors: 3,
        fuelType: "diezel",
        lastServiceDate: new Date(2020,2,2),
        nextServiceDate: new Date(2025,2,2),
        mileage:12123
        // newCar: false
      }
    ]

    return of(mockVozilo);
  }

  getVoziloList(): Observable<Vozilo[]> {
    return this.httpClient.get<Vozilo[]>(`${this.basUrl}`)
  }

  getVoziloById(id: number) {
    return this.httpClient.get<Vozilo>(`${this.basUrl}/${id}`)
  }

  createVozilo(vozilo: Vozilo): Observable<Object>{
    return this.httpClient.post(`${this.basUrl}`, vozilo);
    
  }

  deleteVozilo(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.basUrl}/${id}`)
  } 
}
