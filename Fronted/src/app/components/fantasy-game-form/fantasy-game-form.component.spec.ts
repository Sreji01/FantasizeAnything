import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FantasyGameFormComponent } from './fantasy-game-form.component';

describe('FantasyGameFormComponent', () => {
  let component: FantasyGameFormComponent;
  let fixture: ComponentFixture<FantasyGameFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FantasyGameFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FantasyGameFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
