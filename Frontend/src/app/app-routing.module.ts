import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SplashScreenComponent } from './splash-screen/splash-screen.component';

const routes: Routes = [
  { path : '', redirectTo : '/splash', pathMatch: 'full'},
  { path : 'signup', component : SignupComponent},
  { path : 'profile', component : ProfileComponent},
  { path : 'home', component: HomeComponent},
  { path : 'login', component : LoginComponent},
  { path : 'splash', component : SplashScreenComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
