import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../interfaces/review';
@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private baseUrl = "http://localhost:8080/review";

  constructor(private httpClient: HttpClient) { }

  getReviewList(): Observable<Review[]> {
    return this.httpClient.get<Review[]>(`${this.baseUrl}`)
  }

  getReviewById(id: number) {
    return this.httpClient.get<Review>(`${this.baseUrl}/id/${id}`);
  }

  createReview(review: Review): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}`, review);
  }

  updateReview(id: number, review: Review): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${id}`,review);
  }

  deleteReview(id:number): Observable<Object> {
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}
