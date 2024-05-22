import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { UserInfo } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/auth/api/v1';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/login`, { username, password }, this.httpOptions);
  }

  logout(): Observable<any> {
    return this.http.post(`${this.baseUrl}/logout`, {}, this.httpOptions)
  }

  registerUser(user: UserInfo): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, user)
  } 

  getUsers(): Observable<any> {
    return this.http.get<UserInfo[]>(`${this.baseUrl}/users`);
  }

  refreshToken(): Observable<any> {
    const refreshToken = localStorage.getItem('refreshToken');

    if(!refreshToken) {
      console.error('No refresh token found');
      return throwError(() => new Error('No refresh token found!'))
    }

    const body = {refreshToken: refreshToken, expiredAccessToken: localStorage.getItem('token')};

    return this.http.post<any>(`${this.baseUrl}/refresh`, body).pipe(
      // tap(response => {
      //   if(response && response.token) {
      //     localStorage.setItem('token', response.tokens.accessToken);
      //     localStorage.setItem('refreshToken', response.tokens.refreshToken);
      //   }
      // })
    )
  }
}
