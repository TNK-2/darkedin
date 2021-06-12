import { BookmarkService } from './../../services/bookmark/bookmark.service';
import { Bookmark } from './../../models/bookmark';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-center-bookmarks',
  templateUrl: './center-bookmarks.component.html',
  styleUrls: ['./center-bookmarks.component.css']
})
export class CenterBookmarksComponent implements OnInit {

  bookmarks: { value: Bookmark, isEditing: boolean }[] = [];

  constructor(
    private bookmarkService: BookmarkService
  ) { }

  ngOnInit(): void {
    this.getBookmarks()
  }

  getBookmarks(): void {
    this.bookmarkService
      .getBookMarks()
      .subscribe(bookmarks => bookmarks.map(it => {
        this.bookmarks.push({value: it, isEditing: false})
      }))
  }

  toggleEditing(bookmark: { value: Bookmark, isEditing: boolean }): void {
    bookmark.isEditing = !bookmark.isEditing
  }

  updateBookmark(): void {
    this.bookmarkService.updateBookmark({} as Bookmark);
  }

  deleteBookmark(): void {
    this.bookmarkService.deleteBookmark(1);
  }

}
