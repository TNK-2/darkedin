import { TagService } from './../../services/tag/tag.service';
import { Observable, Subject, of } from 'rxjs';
import { Tag } from './../../models/tag';
import { User, MOCK_USER } from './../../models/user';
import { UserService } from './../../services/user/user.service';
import { BookmarkService } from './../../services/bookmark/bookmark.service';
import { Bookmark } from './../../models/bookmark';
import { Component, OnInit } from '@angular/core';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-center-bookmarks',
  templateUrl: './center-bookmarks.component.html',
  styleUrls: ['./center-bookmarks.component.css']
})
export class CenterBookmarksComponent implements OnInit {

  isSearchAcrive: boolean = false;
  bookmarks: { value: Bookmark, isEditing: boolean }[] = [];
  bookmarks$: Observable<Bookmark[]> = of([]);
  user: User = MOCK_USER;
  tags$: Observable<Tag[]> = of([]);
  private bookmarkSearchTerms = new Subject<string>();
  private tagSearchTerms = new Subject<string>();

  constructor(
    private bookmarkService: BookmarkService,
    private userService: UserService,
    private tagService: TagService
  ) { }

  ngOnInit(): void {
    this.getBookmarks();
    this.getUser();
    this.tags$ = this.tagSearchTerms.pipe(
      // 各キーストロークの後、検索前に300ms待つ
      debounceTime(300),
      // 直前の検索語と同じ場合は無視する
      distinctUntilChanged(),
      // 検索語が変わる度に、新しい検索observableにスイッチする
      switchMap((term: string) => this.tagService.searchByTagNameAndUserId(term, this.user.id)),
    );
    this.bookmarks$ = this.bookmarkSearchTerms.pipe(
      // 各キーストロークの後、検索前に300ms待つ
      debounceTime(300),
      // 直前の検索語と同じ場合は無視する
      distinctUntilChanged(),
      // 検索語が変わる度に、新しい検索observableにスイッチする
      switchMap((term: string) => this.bookmarkService.searchBookMarks(term))
    )
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

  bookmarkSearch(term: string) {
    this.bookmarkSearchTerms.next(term)
  }

  tagSearch(term: string) {
    this.tagSearchTerms.next(term)
  }

  toggleEditing(bookmark: { value: Bookmark, isEditing: boolean }): void {
    bookmark.isEditing = !bookmark.isEditing;
  }

  updateBookmark(bookmark: Bookmark, comment: string): void {
    bookmark.comment = comment;
    this.bookmarkService.updateBookmark(bookmark);
  }

  deleteBookmark(id: number): void {
    this.bookmarkService.deleteBookmark(id);
  }

  isTagged(tagId: number, bookmark: Bookmark): boolean {
    return bookmark.tags.filter(t => t.id == tagId).length > 0
  }

  changeTag(e: any, bookmark: Bookmark, tag: Tag): void {
    if(e.target.cheched && !this.isTagged(tag.id, bookmark)) {
      bookmark.tags.push(tag);
    } else {
      bookmark.tags = bookmark.tags.filter(t => t.id != tag.id);
    }
  }

}
