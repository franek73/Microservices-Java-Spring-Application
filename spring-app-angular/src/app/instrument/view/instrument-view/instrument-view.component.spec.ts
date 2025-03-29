import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstrumentViewComponent } from './instrument-view.component';

describe('InstrumentViewComponent', () => {
  let component: InstrumentViewComponent;
  let fixture: ComponentFixture<InstrumentViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InstrumentViewComponent]
    });
    fixture = TestBed.createComponent(InstrumentViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
