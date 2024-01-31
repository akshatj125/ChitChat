import {
  AfterViewChecked,
  ChangeDetectionStrategy,
  Component,
  ElementRef,
  Input,
  OnInit,
  ViewChild,
} from '@angular/core';
import { ChatService } from './chat.service';
import { ConversationDto } from '../interfaces/conversationDto';
import { MessageService } from './message.service';
import { WebSocketService } from './web-socket.service';
import { Profile } from '../interfaces/profile';
import { DatePipe } from '@angular/common';
import { FileUploadService } from './file-upload.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
  changeDetection: ChangeDetectionStrategy.Default,
})
export class ChatComponent implements OnInit, AfterViewChecked {
  conversations: ConversationDto[] = [];
  profile: Profile[] = [];
  highlightChatBox: boolean;
  highlightedIndex: number;
  @ViewChild('messageBox') elementRef: ElementRef;

  newConversation;
  newMessage: String = '';
  username = '';
  rightBox: Boolean = false;
  searchText: string = '';
  @Input() profileImageUrl: any = null;

  constructor(
    private chatService: ChatService,
    private messageService: MessageService,
    private webSocketService: WebSocketService,
    private datePipe: DatePipe,
    private fileUploadService: FileUploadService
  ) {
    this.username = localStorage.getItem('username');
  }

  handleHighlightChatBoxChange(value: boolean): void {
    this.highlightChatBox = value;
  }

  handleHighlightIndexChange(value: number): void {
    this.highlightedIndex = value;
  }

  handleNewConversation(newConv: ConversationDto) {
    this.newConversation = newConv;
  }

  ngOnInit() {
    this.loadConversations();
  }

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  loadConversations() {
    if (this.searchText.trim() === '') {
      this.chatService.getConversations().subscribe(
        (conversations: ConversationDto[]) => {
          this.conversations = conversations.filter(
            (conv) => conv.messages && conv.messages.length > 0
          );
        },
        (error) => {
          console.error('Error fetching conversations:', error);
        }
      );
    } else {
      this.chatService.getConversations().subscribe(
        (conversations: ConversationDto[]) => {
          this.conversations = conversations.filter(
            (conv) =>
              conv.name.toLowerCase().includes(this.searchText.toLowerCase()) &&
              conv.messages &&
              conv.messages.length > 0
          );
        },
        (error) => {
          console.error('Error fetching conversations:', error);
        }
      );
    }

    this.webSocketService.getMessages().subscribe((message: any) => {
      message = JSON.parse(message);
      const targetConversation = this.conversations.find(
        (conversation) => conversation.id === message.conversationId
      );

      if (targetConversation) {
        targetConversation.messages.push(message);
        console.log('Message aa gya');
      } else {
        console.error('Conversation not found');
      }
    });
  }

  onSearchTextEntered(searchValue: string) {
    this.searchText = searchValue;
    this.highlightedIndex = NaN;
    this.loadConversations();
  }

  chatBoxClicked(index: number) {
    this.highlightChatBox = true;
    this.highlightedIndex = index;
  }

  getLatestMessageTime(messages: any[]) {
    if (messages.length > 0) {
      const latestMessage = messages[messages.length - 1];
      return this.datePipe.transform(latestMessage.sentAt, 'hh:mm a');
    }
    return false;
  }

  getLatestMessage(messages: any[]) {
    if (messages.length > 0) {
      const latestMessage = messages[messages.length - 1];
      return latestMessage.message;
    }
    return 'No messages';
  }

  idSelect(): number {
    if (
      this.conversations[this.highlightedIndex] &&
      this.conversations[this.highlightedIndex]?.id
    ) {
      console.log('inside id select');

      return this.conversations[this.highlightedIndex]?.id;
    } else {
      return this.newConversation?.id;
    }
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    this.sendFile(file);
  }

  sendFile(file: File) {
    if (file) {
      this.fileUploadService.uploadFile(file).subscribe(
        (res) => {
          console.log('File uploaded successfully:', res);
        },
        (error) => {
          console.log('File not uploaded:', error);
        }
      );
    }
  }

  sendMessage() {
    if (this.newMessage.trim() !== '') {
      this.messageService
        .sendMessage(this.newMessage, this.idSelect(), this.username)
        .subscribe(
          (response) => {
            console.log('Message sent successfully:', response);
            this.newMessage = '';

            this.chatService.getConversations().subscribe(
              (conversations: ConversationDto[]) => {
                this.conversations = conversations.filter(
                  (conv) => conv.messages && conv.messages.length > 0
                );

                const isNewConversation = this.newConversation !== undefined;
                if (isNewConversation) {
                  this.highlightedIndex = this.conversations.length - 1;
                } else {
                  this.conversations.findIndex(
                    (conv) => conv.id === this.idSelect()
                  );
                }
                console.log('Updated highlightedIndex:', this.highlightedIndex);
              },
              (error) => {
                console.error('Error fetching conversations:', error);
              }
            );
          },
          (error) => {
            console.error('Error sending message:', error);
          }
        );
    } else {
      this.newMessage = '';
    }
  }

  scrollToBottom() {
    this.elementRef.nativeElement.scrollTop =
      this.elementRef.nativeElement.scrollHeight;
  }
}
