import { TestBed } from '@angular/core/testing';

import { SoftwareDeveloperService } from './software-developer.service';

describe('SoftwareDeveloperService', () => {
  let service: SoftwareDeveloperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SoftwareDeveloperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
