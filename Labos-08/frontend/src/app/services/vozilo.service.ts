import { Injectable } from '@angular/core';

import { Vozilo } from '../interfaces/vozilo';
import { Review } from '../interfaces/review';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VoziloService {

  private basUrl = "http://localhost:8080/vozilo"
  private basReview = "http://localhost:8080/review"

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
    // const jwtToken = localStorage.getItem('JWT')
    // const headers = new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`)
    return this.httpClient.get<Vozilo[]>(`${this.basUrl}`)
  }

  getVoziloById(id: number) {
    return this.httpClient.get<Vozilo>(`${this.basUrl}/${id}`)
  }

  createVozilo(vozilo: Vozilo): Observable<Object>{
    return this.httpClient.post(`${this.basUrl}`, vozilo);
  }

  getVoziloByRegistration(registration: string): Observable<Vozilo>{
    return this.httpClient.get<Vozilo>(`${this.basUrl}/registration/${registration}`);
  }

  //reviews

  getReviewsByVoziloRegistration(registration: string): Observable<Review[]> {
    return this.httpClient.get<Review[]>(`${this.basReview}/${registration}`);
  }

  deleteVozilo(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.basUrl}/${id}`)
  } 

  private createAuthorizationHeader() {
    const jwtToken = localStorage.getItem('JWT');
    if(jwtToken) {
      let headers = new HttpHeaders()
      headers = headers.set('Authorization', `Bearer ${jwtToken}`)
    }
  }

}
