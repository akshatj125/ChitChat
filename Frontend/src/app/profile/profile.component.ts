import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  constructor(private userService : UserService ,private http : HttpClientModule){}

  userdata: any={
    username:"",
    email:""
  };

  getUserData(){
    this.userService.getUserData().subscribe((data) => {
      this.userdata = data;
    });
  }

  ngOnInit(): void {
      this.getUserData();
  }

}
