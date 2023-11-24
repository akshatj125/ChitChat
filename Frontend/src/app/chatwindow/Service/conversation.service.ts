import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ConversationService {
  private token: string | null;

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  getConvData(): Observable<any> {
    const url="http://localhost:8080/users/conversations"
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.token}`,
    });

    return this.http.get(url, { headers });
  }
}

