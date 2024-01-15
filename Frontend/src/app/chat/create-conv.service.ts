import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConversationDto } from '../interfaces/conversationDto';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CreateConvService {
  constructor(private http: HttpClient) {}

  private apiUrl = 'http://localhost:8080/createConversations';

  createConversation(username: String): Observable<ConversationDto> {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );
    const requestBody = username;
    return this.http.post<ConversationDto>(this.apiUrl, requestBody, { headers });
  }
}
