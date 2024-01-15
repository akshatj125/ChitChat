import { HttpClientModule } from '@angular/common/http';
import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import { Router } from '@angular/router';
import { ConversationDto } from '../interfaces/conversationDto';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  @Input() highlightChatBox: boolean;
  @Input() highlightedIndex: number;

  @Output() highlightChatBoxChange = new EventEmitter<boolean>();
  @Output() highlightedIndexChange = new EventEmitter<number>();

  @Input() newConv;
  @Output() newConversationCreated = new EventEmitter<ConversationDto>();

  constructor(private router: Router, private http: HttpClientModule) {}
  ngOnInit(): void {}

  handleHighlightChatBoxChange(value: boolean): void {
    this.highlightChatBox = value;

    this.highlightChatBoxChange.emit(this.highlightChatBox);
    // console.log(this.highlightChatBox);
  }

  handleHighlightIndexChange(value: number): void {
    this.highlightedIndex = value;

    this.highlightedIndexChange.emit(this.highlightedIndex);
    // console.log(this.highlightedIndex);
  }
  
  handleNewConversation(newConversation: ConversationDto) {
    this.newConv = newConversation;
    this.newConversationCreated.emit(this.newConv)
  }
  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  isLoggedIn() {
    return !!localStorage.getItem('token');
  }

  navigateBasedOnLoginStatus() {
    if (this.isLoggedIn()) {
      this.router.navigate(['/chat']);
    } else {
      this.router.navigate(['/splash']);
    }
  }
}
