import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartialOrderComponent } from './partial-order.component';

describe('PartialOrderComponent', () => {
  let component: PartialOrderComponent;
  let fixture: ComponentFixture<PartialOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PartialOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PartialOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
