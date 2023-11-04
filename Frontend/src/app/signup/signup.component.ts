import {
  Component,
  EventEmitter,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { NgForm, NgModel } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  userdata = {
    username: '',
    email: '',
    password: '',
  };

  constructor(private http: HttpClient) {}
  signup() {
    const headers=new HttpHeaders({
      'Content-Type':'application/json'
    })    
    let response = this.http.post(
      'http://localhost:8080/users/signup',
      this.userdata,
      {headers,responseType:'text'}
    );
    response.subscribe((data) => {
      console.log(data);
      localStorage.setItem('token', data);
    });
  }
}
