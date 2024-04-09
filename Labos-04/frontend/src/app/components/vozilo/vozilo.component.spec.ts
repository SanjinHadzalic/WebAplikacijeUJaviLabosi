import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoziloComponent } from './vozilo.component';

describe('VoziloComponent', () => {
  let component: VoziloComponent;
  let fixture: ComponentFixture<VoziloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VoziloComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VoziloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
