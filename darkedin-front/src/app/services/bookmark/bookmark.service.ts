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

  searchBookMarks(tarm: string): Observable<Bookmark[]>{
    return of(MOCK_BOOKMARKS);
  }

  addBookmark(url: string): void {
    console.log("addBookmark!");
  }

  updateBookmark(bookmark: Bookmark) {
    console.log("updateBookmark!");
  }

  deleteBookmark(id: number): void {
    console.log("deleteBookmark!");
  }

}
