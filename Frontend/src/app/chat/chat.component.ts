import { Component, OnInit } from '@angular/core';
import { ChatService } from './chat.service';
import { ConversationDto } from '../interfaces/conversationDto';
import { DatePipe } from '@angular/common';
import { MessageService } from './message.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
  conversations: ConversationDto[] = [];
  highightChatBox = false;
  highlightedIndex = NaN;
  allMessages = [];

  newMessage: String = '';

  constructor(
    private chatService: ChatService,
    private datePipe: DatePipe,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.loadConversations();
  }

  loadConversations() {
    this.chatService.getConversations().subscribe(
      (conversations: ConversationDto[]) => {
        this.conversations = conversations;
      },
      (error) => {
        console.error('Error fetching conversations:', error);
      }
    );
  }

  chatBoxClicked(index: number) {
    this.highightChatBox = true;
    this.highlightedIndex = index;
  }

  getLatestMessageTime(messages: any[]): string {
    if (messages && messages.length > 0) {
      const latestMessage = messages[messages.length - 1];
      return this.datePipe.transform(latestMessage.timestamp, 'hh:mm a');
    }
    return 'No messages';
  }

  getLatestMessage(messages: any[]) {
    if (messages && messages.length > 0) {
      const latestMessage = messages[messages.length - 1];
      return latestMessage.message;
    }
  }

  sendMessage() {
    if (this.newMessage.trim() !== '') {
      this.messageService
        .sendMessage(
          this.newMessage,
          this.conversations[this.highlightedIndex]?.id
        )
        .subscribe(
          (data) => {
            console.log('Message sent successfully:', data);
            this.newMessage = '';
          },
          (error) => {
            console.error('Error sending message:', error);
            console.log(error);
          }
        );
    } else {
      this.newMessage = '';
    }
  }
}
