import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CenterFeedComponent } from './center-feed.component';

describe('CenterFeedComponent', () => {
  let component: CenterFeedComponent;
  let fixture: ComponentFixture<CenterFeedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CenterFeedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CenterFeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
