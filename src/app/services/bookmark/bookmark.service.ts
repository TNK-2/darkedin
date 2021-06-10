import { Bookmark, MOCK_BOOKMARK, MOCK_BOOKMARKS } from './../../models/bookmark';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookmarkService {

  constructor() { }

  getBookMarks(): Observable<Bookmark[]>{
    return of(MOCK_BOOKMARKS);
  }

  addBookmark(url: string) {
    const bookMark = this.getBookMarkDetal(url);
    // push bookmark
  }

  private getBookMarkDetal(url: string): Bookmark {
    return MOCK_BOOKMARK;
  }
}
