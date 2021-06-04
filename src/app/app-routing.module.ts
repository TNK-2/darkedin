import { CenterFeedComponent } from './center-feed/center-feed.component';
import { CenterBookmarksComponent } from './center-bookmarks/center-bookmarks.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'feed', component: CenterFeedComponent },
  { path: 'bookmarks', component: CenterBookmarksComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
