import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CenterBookmarksComponent } from './center-bookmarks.component';

describe('CenterBookmarksComponent', () => {
  let component: CenterBookmarksComponent;
  let fixture: ComponentFixture<CenterBookmarksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CenterBookmarksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CenterBookmarksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
