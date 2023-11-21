import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8080';
  private token: string | null;

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  getUserData(): Observable<any> {
    // const url = `${this.baseUrl}/users/users`;
    const url="http://localhost:8080/users/profile"
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.token}`,
    });

    return this.http.get(url, { headers });
  }
}

