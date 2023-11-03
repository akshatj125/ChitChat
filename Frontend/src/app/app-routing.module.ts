import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path : '', redirectTo : '/signup', pathMatch: 'full'},
  { path : 'signup', component : SignupComponent},
  { path : 'profile', component : ProfileComponent},
  { path : 'home', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
