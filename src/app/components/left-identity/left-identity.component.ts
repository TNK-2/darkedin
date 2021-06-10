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

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  getLoginUser(): void {
    this.userService
      .getUser()
      .subscribe(user => this.user = user)
  }

}
