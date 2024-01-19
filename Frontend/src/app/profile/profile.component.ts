import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { UserService } from './user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  constructor(private userService: UserService) {}

  profileImageUrl: any = null;
  userdata: any = {
    username: '',
    email: '',
  };

  selectedProfilePicture: File;

  ngOnInit(): void {
    this.getProfilePicture();
    this.getUserData();
  }

  getUserData() {
    this.userService.getUserData().subscribe((data) => {
      // console.log('Received data:', data);
      this.userdata = data;
    });
  }

  updateProfilePicture() {
    if (this.selectedProfilePicture) {
      this.userService
        .uploadProfilePicture(this.selectedProfilePicture)
        .subscribe((response) => {
          console.log('Profile picture uploaded:', response);
          this.getProfilePicture();
        });
    }
  }

  getProfilePicture() {
    this.userService.getProfilePicture().subscribe(
      (data) => {
        // console.log(data);
        const imageUrl = URL.createObjectURL(
          new Blob([data], { type: 'image/png' })
        );
        this.profileImageUrl = imageUrl;
      },
      (error) => {
        this.profileImageUrl =
          'https://bootstrapious.com/i/snippets/sn-chat/avatar.svg';
      }
    );
  }

  onProfilePictureSelected(event: any) {
    const file: File = event.target.files[0];
    this.selectedProfilePicture = file;
  }
}
