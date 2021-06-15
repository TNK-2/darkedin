import { Observable, of } from 'rxjs';
import { MOCK_TAGS, Tag } from './../../models/tag';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor() { }

  searchByTagNameAndUserId(name: string, userId: string): Observable<Tag[]> {
    console.log("searchByTagNameAndUserId!")
    return of(MOCK_TAGS)
  }

  addTag(name: string): void {
    MOCK_TAGS.push({id: 4, name: name, count: 0})
  }
}
