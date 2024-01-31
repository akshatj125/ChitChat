import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';

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
    const url = 'http://localhost:8080/users/profile';
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
    });

    return this.http.get(url, { headers });
  }

  searchUsers(query: string): Observable<any> {
    const url = `http://localhost:8080/users/search?query=${query}`;
    // console.log(url)

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${this.token}`,
    });

    return this.http.get(url, { headers });
  }

  uploadProfilePicture(file: File): Observable<any> {
    const url = `${this.baseUrl}/users/profile_picture`;
    const formData: FormData = new FormData();
    formData.append('profilePicture', file, file.name);

    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
    });

    return this.http.post(url, formData, { headers, responseType: 'text' });
  }

  getProfilePicture(): Observable<any> {
    const url = 'http://localhost:8080/users/profile_picture';
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
    });
    return this.http.get(url, { headers, responseType: 'blob' });
  }
}
