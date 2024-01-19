import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  Subject,
  catchError,
  debounceTime,
  distinctUntilChanged,
  switchMap,
} from 'rxjs';
import { UserService } from '../profile/user.service';
import { CreateConvService } from '../chat/create-conv.service';
import { ConversationDto } from '../interfaces/conversationDto';
import { ChatService } from '../chat/chat.service';

@Component({
  selector: 'app-user-search',
  templateUrl: './user-search.component.html',
  styleUrls: ['./user-search.component.css'],
})
export class UserSearchComponent {
  @Input() highlightChatBox: boolean;
  @Input() highlightedIndex: number;

  @Output() highlightChatBoxChange = new EventEmitter<boolean>();
  @Output() highlightedIndexChange = new EventEmitter<number>();
  @Output() newConversationCreated = new EventEmitter<ConversationDto>();

  query: string = '';
  searchResults: any[] = [];
  conversations: ConversationDto[] = [];
  private searchTerms = new Subject<string>();

  constructor(
    private userService: UserService,
    private createConvService: CreateConvService,
    private chatService: ChatService
  ) {
    this.searchTerms
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
        switchMap((term: string) => {
          if (term.trim() === '') {
            this.searchResults = [];
            return [];
          }
          return this.userService.searchUsers(term).pipe(
            catchError((error) => {
              console.error('Error during search:', error);
              return [];
            })
          );
        })
      )
      .subscribe((results) => {
        this.searchResults = results;
        // console.log(this.searchResults);
      });
  }

  search(): void {
    this.searchTerms.next(this.query);
  }

  startConversation(name: string): void {
    this.createConvService.createConversation(name).subscribe(
      (newConversation: ConversationDto) => {
        console.log('New conversation created:', newConversation);

        this.chatService
          .getConversations()
          .subscribe((conversations: ConversationDto[]) => {
            const existingConversationIndex = conversations.findIndex(
              (conv) => conv.name === newConversation.name
            );

            if (existingConversationIndex !== -1) {
              this.highlightedIndex = existingConversationIndex;
            }

            this.query = '';
            this.searchResults = [];
            this.highlightChatBox = true;
            this.highlightChatBoxChange.emit(this.highlightChatBox);
            this.highlightedIndexChange.emit(this.highlightedIndex);
            this.newConversationCreated.emit(newConversation); 
          });
      },
      (error) => {
        console.error('Error creating conversation:', error);
      }
    );
  }
}
