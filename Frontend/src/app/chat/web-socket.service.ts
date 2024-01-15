import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import * as Stomp from 'stompjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  stompClient = Stomp.client('ws://localhost:8080/ws');
  username = '';

  receivedMessages = new Subject();

  constructor() {
    this.username = localStorage.getItem('username');
    this.connectWebSocket();
  }

  connectWebSocket() {
    this.stompClient.connect(
      {},
      () => {
        this.stompClient.subscribe('/topic/chat/' + this.username, (data) => {
          // console.log(data);
          this.receivedMessages.next(data.body);
        });
      },
      () => {
        console.log('Disconnected');
      }
    );
  }

  getMessages() {
    return this.receivedMessages;
  }

}
