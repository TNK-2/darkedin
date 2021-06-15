import { TagService } from './../../services/tag/tag.service';
import { Tag } from './../../models/tag';
import { BookmarkService } from './../../services/bookmark/bookmark.service';
import { UserService } from './../../services/user/user.service';
import { User } from 'src/app/models/user';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-left-identity',
  templateUrl: './left-identity.component.html',
  styleUrls: ['./left-identity.component.css']
})
export class LeftIdentityComponent implements OnInit {

  user?: User;

  constructor(
    private userService: UserService,
    private bookmarkService: BookmarkService,
    private tagService: TagService
  ) { }

  ngOnInit(): void {
    this.getLoginUser()
  }

  getLoginUser(): void {
    this.userService
      .getUser()
      .subscribe(user => this.user = user)
  }

  addBookMark(url: string): void {
    url = url.trim()
    if (!url) return;
    this.bookmarkService.addBookmark(url)
  }

  addTag(tagName: string): void {
    tagName = tagName.trim()
    if (!tagName) return;
    this.tagService.addTag(tagName)
  }

}
