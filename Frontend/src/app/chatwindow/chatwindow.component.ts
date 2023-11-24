import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ConversationService } from './Service/conversation.service';
import { conversation } from '../interfaces/conversation';

@Component({
  selector: 'app-chatwindow',
  templateUrl: './chatwindow.component.html',
  styleUrls: ['./chatwindow.component.css'],
})
export class ChatwindowComponent implements OnInit {

  constructor(
    private http: HttpClientModule,
    private conversationService: ConversationService
  ) {}

  convdata : any = {
    name: ""
  };
  
  getConvData() {
    this.conversationService.getConvData().subscribe((data) => {
      console.log(data);
      this.convdata = data;
    });
  }

  ngOnInit(): void {
    this.getConvData();
  }
}
