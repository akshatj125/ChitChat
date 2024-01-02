import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConversationDto } from '../interfaces/conversationDto';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  private apiUrl = 'http://localhost:8080/sendMessage';

  constructor(private http: HttpClient) {}

  sendMessage(newMessage: String, conversationId: number): Observable<any> {
    const endpoint = this.apiUrl;
    const payload = { message: newMessage, conversationId: conversationId };

    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );

    return this.http.post(endpoint, payload, { headers, responseType: 'text'});
  }
}
