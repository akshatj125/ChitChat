import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConversationDto } from '../interfaces/conversationDto';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private apiUrl = 'http://localhost:8080/users/conversations';

  constructor(private http: HttpClient) {}

  getConversations(): Observable<ConversationDto[]> {
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    return this.http.get<ConversationDto[]>(this.apiUrl, {headers});
  }
}
