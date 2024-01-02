import { HttpClientModule } from '@angular/common/http';
import { Component, ElementRef, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  constructor(private router: Router, private http: HttpClientModule) {}

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  isLoggedIn() {
    return !!localStorage.getItem('token');
  }

  navigateBasedOnLoginStatus() {
    if (this.isLoggedIn()) {
      this.router.navigate(['/home']);
    } else {
      this.router.navigate(['/splash']);
    }
  }
}
