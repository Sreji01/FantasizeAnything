import { TestBed } from '@angular/core/testing';

import { FantasyGameService } from './fantasy-game.service';

describe('FantasyGameService', () => {
  let service: FantasyGameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FantasyGameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
