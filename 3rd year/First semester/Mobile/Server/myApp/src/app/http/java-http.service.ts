import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpBaseService } from './http-base.service';

export const environment = {
  production: false,
  javaServerAddress: 'http://192.168.1.16:8080',
};

@Injectable({providedIn: 'root'})
export class JavaHttpService extends HttpBaseService {

  constructor(protected override http: HttpClient) {
    super(http);
  }

  protected getBasePath(): string {
    return environment.javaServerAddress;
  }
}
