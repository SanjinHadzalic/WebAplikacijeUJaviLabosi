import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../interfaces/review';
@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private baseUrl = "http://localhost:8082/review";

  constructor(private HttpClient: HttpClient) { }

  getReviewList(): Observable<Review[]> {
    return this.HttpClient.get<Review[]>(`${this.baseUrl}`)
  }

  getReviewById(id: number) {
    return this.HttpClient.get<Review>(`${this.baseUrl}/id/${id}`);
  }

  createReview(review: Review): Observable<Object> {
    return this.HttpClient.post(`${this.baseUrl}`, review);
  }

  updateReview(id: number, review: Review): Observable<Object> {
    return this.HttpClient.put(`${this.baseUrl}/${id}`,review);
  }

  deleteReview(id:number): Observable<Object> {
    return this.HttpClient.delete(`${this.baseUrl}/${id}`);
  }
}
