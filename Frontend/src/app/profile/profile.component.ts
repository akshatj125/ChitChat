import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from './Service/user.service';

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
      console.log(data[0])
      this.userdata = data;
    });
  }

  ngOnInit(): void {
      this.getUserData();
  }

}
