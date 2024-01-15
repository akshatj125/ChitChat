import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  constructor(private http: HttpClient) {}

  sendMessage(
    newMessage: String,
    conversationId: number,
    senderUsername: String
  ): Observable<any> {
    const endpoint = 'http://localhost:8080/sendMessage';
    const payload = {
      message: newMessage,
      conversationId: conversationId,
      senderUsername: senderUsername,
    };

    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );

    return this.http.post(endpoint, payload, { headers, responseType: 'text' });
  }

  updateStatus(messageId: number, status: boolean): Observable<any> {
    const url = `http://localhost:8080/update?status=${status}`;
    
    const body = { messageId: messageId };
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('token')
    );

    return this.http.put(url, body, { headers, responseType: 'text' })
  }
}
