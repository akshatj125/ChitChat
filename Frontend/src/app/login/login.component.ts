import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

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

  onUsernameChange() {
    this.userdata.username = this.userdata.username.toLowerCase();
  }


  constructor(private router : Router, private http: HttpClient) {}
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
      localStorage.setItem('username', this.userdata.username);

      var url=localStorage.getItem("redirectUrl")
        if(url==null)
        {
          url="/home"
        }
        localStorage.removeItem("redirectUrl")
        this.router.navigate(['/home']);
      
      },
      error=>{
        alert("Invalid credentials")
      }
    )
    }
}
