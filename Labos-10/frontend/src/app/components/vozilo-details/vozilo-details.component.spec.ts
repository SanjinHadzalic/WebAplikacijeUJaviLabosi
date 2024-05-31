import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoziloDetailsComponent } from './vozilo-details.component';

describe('VoziloDetailsComponent', () => {
  let component: VoziloDetailsComponent;
  let fixture: ComponentFixture<VoziloDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VoziloDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VoziloDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
