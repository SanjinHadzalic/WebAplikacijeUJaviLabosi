import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoziloNextComponent } from './vozilo-next.component';

describe('VoziloNextComponent', () => {
  let component: VoziloNextComponent;
  let fixture: ComponentFixture<VoziloNextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VoziloNextComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VoziloNextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
