import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { CenterFeedComponent } from './center-feed/center-feed.component';
import { LeftIdentityComponent } from './left-identity/left-identity.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CenterFeedComponent,
    LeftIdentityComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
