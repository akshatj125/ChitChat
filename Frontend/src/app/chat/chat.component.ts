import {
  AfterViewChecked,
  ChangeDetectionStrategy,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import { ChatService } from './chat.service';
import { ConversationDto } from '../interfaces/conversationDto';
import { DatePipe, getLocaleMonthNames } from '@angular/common';
import { MessageService } from './message.service';
import { WebSocketService } from './web-socket.service';
import { UserService } from '../profile/Service/user.service';
import { Profile } from '../interfaces/profile';
import { SearchService } from '../search/search.service';
import { filter } from 'rxjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
  changeDetection: ChangeDetectionStrategy.Default,
})
export class ChatComponent implements OnInit, AfterViewChecked {
  conversations: ConversationDto[] = [];
  profile: Profile[] = [];
  highlightChatBox = false;
  highlightedIndex = NaN;
  @ViewChild('messageBox') elementRef: ElementRef;

  newMessage: String = '';
  username = '';
  displayName = '';
  rightBox: Boolean = false;
  searchText: string = '';

  constructor(
    private chatService: ChatService,
    private datePipe: DatePipe,
    private messageService: MessageService,
    private webSocketService: WebSocketService,
    private searchService: SearchService
  ) {
    this.username = localStorage.getItem('username');
  }

  ngOnInit() {
    this.loadConversations();
  }

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  // loadConversations() {
  //   this.chatService.getConversations().subscribe(
  //     (conversations: ConversationDto[]) => {
  //       this.conversations = conversations;
  //     },
  //     (error) => {
  //       console.error('Error fetching conversations:', error);
  //     }
  //   );

  //   this.webSocketService.getMessages().subscribe((message: any) => {
  //     message = JSON.parse(message);
  //     message.self = message.senderUsername === this.username;
  //     this.conversations[this.highlightedIndex]?.messages.push(message);
  //   });
  // }

  loadConversations() {
    if (this.searchText.trim() === '') {
      this.chatService.getConversations().subscribe(
        (conversations: ConversationDto[]) => {
          this.conversations = conversations;
        },
        (error) => {
          console.error('Error fetching conversations:', error);
        }
      );

    } else {
      this.chatService.getConversations().subscribe(
        (conversations: ConversationDto[]) => {
          this.conversations = conversations.filter((conversation) =>
            conversation.name
              .toLowerCase()
              .includes(this.searchText.toLowerCase())
          );
        },
        (error) => {
          console.error('Error fetching conversations:', error);
        }
      );
    }

    this.webSocketService.getMessages().subscribe((message: any) => {
      message = JSON.parse(message);
      message.self = message.senderUsername === this.username;
      this.conversations[this.highlightedIndex]?.messages.push(message);
    });
  }

  onSearchTextEntered(searchValue: string) {
    this.searchText = searchValue;
    this.highlightedIndex = NaN; // Reset highlightedIndex when search text changes
    this.loadConversations();
  }

  chatBoxClicked(index: number) {
    this.highlightChatBox = true;
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
          this.conversations[this.highlightedIndex]?.id,
          this.displayName
        )
        .subscribe(
          (response) => {
            console.log('Message sent successfully:', response);
            this.newMessage = '';
          },
          (error) => {
            console.error('Error sending message:', error);
          }
        );
    } else {
      this.newMessage = '';
    }
  }

  // updateConversation(message: any) {
  //   const convToUpdate = this.conversations[this.highlightedIndex];
  //   if (convToUpdate === message.conversationId) {
  //     convToUpdate.messages.push({
  //       message: message.message,
  //       timestamp: message.timestamp,
  //     });
  //   }
  //   this.highlightedIndex = this.conversations.indexOf(convToUpdate);
  //   this.scrollToBottom();
  // }

  scrollToBottom() {
    this.elementRef.nativeElement.scrollTop =
      this.elementRef.nativeElement.scrollHeight;
  }
}
