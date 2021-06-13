import { TagService } from './../../services/tag/tag.service';
import { Observable, Subject, of } from 'rxjs';
import { Tag } from './../../models/tag';
import { User, MOCK_USER } from './../../models/user';
import { UserService } from './../../services/user/user.service';
import { BookmarkService } from './../../services/bookmark/bookmark.service';
import { Bookmark } from './../../models/bookmark';
import { Component, OnInit } from '@angular/core';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-center-bookmarks',
  templateUrl: './center-bookmarks.component.html',
  styleUrls: ['./center-bookmarks.component.css']
})
export class CenterBookmarksComponent implements OnInit {

  bookmarks: { value: Bookmark, isEditing: boolean }[] = [];
  user: User = MOCK_USER;
  tags$: Observable<Tag[]> = of([]);
  private searchTerms = new Subject<string>();

  constructor(
    private bookmarkService: BookmarkService,
    private userService: UserService,
    private tagService: TagService
  ) { }

  ngOnInit(): void {
    this.getBookmarks();
    this.getUser();
    this.tags$ = this.searchTerms.pipe(
      // 各キーストロークの後、検索前に300ms待つ
      debounceTime(300),
      // 直前の検索語と同じ場合は無視する
      distinctUntilChanged(),
      // 検索語が変わる度に、新しい検索observableにスイッチする
      switchMap((term: string) => this.tagService.searchByTagNameAndUserId(term, this.user.id)),
    );
  }

  getBookmarks(): void {
    this.bookmarkService
      .getBookMarks()
      .subscribe(bookmarks => bookmarks.map(it => {
        this.bookmarks.push({value: it, isEditing: false})
      }));
  }

  getUser(): void {
    this.userService
      .getUser()
      .subscribe(user => {
        this.user = user;
      })
  }

  search(term: string) {
    this.searchTerms.next(term)
  }

  toggleEditing(bookmark: { value: Bookmark, isEditing: boolean }): void {
    bookmark.isEditing = !bookmark.isEditing;
  }

  updateBookmark(bookmark: Bookmark): void {
    this.bookmarkService.updateBookmark(bookmark);
  }

  deleteBookmark(id: number): void {
    this.bookmarkService.deleteBookmark(id);
  }

}
