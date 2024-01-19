import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SignupComponent } from './signup/signup.component';
import { ProfileComponent } from './profile/profile.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { ChatComponent } from './chat/chat.component';
import { SplashScreenComponent } from './splash-screen/splash-screen.component';
import { DatePipe } from '@angular/common';
import { SearchComponent } from './search/search.component';
import { UserSearchComponent } from './user-search/user-search.component';
import { HttpCustomInterceptor } from './http-interceptor.interceptor';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    ProfileComponent,
    FooterComponent,
    LoginComponent,
    ChatComponent,
    SplashScreenComponent,
    SearchComponent,
    UserSearchComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    DatePipe, {provide : HTTP_INTERCEPTORS, useClass : HttpCustomInterceptor, multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
