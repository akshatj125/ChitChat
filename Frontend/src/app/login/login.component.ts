import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  userdata = {
    username: '',
    email: '',
    password: '',
  };

  constructor(private http: HttpClient) {}
  login() {
    const headers=new HttpHeaders({
      'Content-Type':'application/json'
    })    
    let response = this.http.post(
      'http://localhost:8080/users/login',
      this.userdata,
      {headers,responseType:'text'}
    );
    response.subscribe((data) => {
      console.log(data);
      localStorage.setItem('token', data);
    });
  }
  
}
