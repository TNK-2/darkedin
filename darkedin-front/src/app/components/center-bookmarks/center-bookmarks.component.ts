import { BookmarkService } from './../../services/bookmark/bookmark.service';
import { Bookmark } from './../../models/bookmark';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-center-bookmarks',
  templateUrl: './center-bookmarks.component.html',
  styleUrls: ['./center-bookmarks.component.css']
})
export class CenterBookmarksComponent implements OnInit {

  bookmarks: Bookmark[] = [];

  constructor(
    private bookmarkService: BookmarkService
  ) { }

  ngOnInit(): void {
    this.getBookmarks()
  }

  getBookmarks(): void {
    this.bookmarkService
      .getBookMarks()
      .subscribe(bookmarks => this.bookmarks = bookmarks)
  }

  updateBookmark(): void {
    this.bookmarkService.updateBookmark({} as Bookmark);
  }

  deleteBookmark(): void {
    this.bookmarkService.deleteBookmark(1);
  }

}
