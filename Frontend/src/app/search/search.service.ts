import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private apiUrl = 'http://localhost:8080/users/users';
  token: string | null;

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token');
  }

  getSearchValue(): Observable<any> {
    const url= this.apiUrl;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.token}`,
    });

    return this.http.get(url, { headers });
  }
  
}
