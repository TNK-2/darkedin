import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeftIdentityComponent } from './left-identity.component';

describe('LeftIdentityComponent', () => {
  let component: LeftIdentityComponent;
  let fixture: ComponentFixture<LeftIdentityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeftIdentityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LeftIdentityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
