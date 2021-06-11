import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { CenterFeedComponent } from './components/center-feed/center-feed.component';
import { LeftIdentityComponent } from './components/left-identity/left-identity.component';
import { CenterBookmarksComponent } from './components/center-bookmarks/center-bookmarks.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CenterFeedComponent,
    LeftIdentityComponent,
    CenterBookmarksComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
