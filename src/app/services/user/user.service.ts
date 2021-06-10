import { Injectable } from '@angular/core';
import { MOCK_USER, User } from 'src/app/models/user';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  getUserInfo(): Observable<User>{
    return of(MOCK_USER);
  }
}
