import { Component, OnInit, OnDestroy } from '@angular/core';
import { ConversationService } from './Service/conversation.service';
import { WebSocketService } from './ChatService/Service/chat.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-chatwindow',
  templateUrl: './chatwindow.component.html',
  styleUrls: ['./chatwindow.component.css'],
})
export class ChatwindowComponent implements OnInit, OnDestroy {
  private ngUnsubscribe = new Subject();

  constructor(
    private conversationService: ConversationService,
    private webSocketService: WebSocketService
  ) {}

  convdata: any = {
    name: '',
  };
  messages: any[] = [];
  newMessage: string = '';

  getConvData() {
    this.conversationService.getConvData().subscribe((data) => {
      console.log(data);
      this.convdata = data;
    });
  }

  ngOnInit(): void {
    this.getConvData();

    this.webSocketService.connect('ws://localhost:8080/ws');
    this.webSocketService.onMessage()
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((message) => {
        console.log('Received message:', message);
      });
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
    this.webSocketService.closeConnection();
  }

  sendMessage(): void {
    if (this.newMessage.trim() !== '') {
      const message = {
        content: this.newMessage,
        sender: 'self',
        timestamp: new Date(),
      };
      this.webSocketService.sendMessage(message);
      this.newMessage = '';
    }
  }
}
