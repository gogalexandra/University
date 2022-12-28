import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { AddProjectDTO } from '../dto/AddProjectDTO';
import { Project } from '../models/Project';
import { SoftWareDeveloper } from '../models/SoftwareDeveloper';

@Injectable({
  providedIn: 'root'
})
export class SoftwareDeveloperService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private developerURL = 'http://localhost:8080/api/developer';

  constructor(private httpClient: HttpClient) {
  }

  public getSoftwareDeveloperByName(name: string): Observable<any> {
    const url = `${this.developerURL}/${name}`;
    return this.httpClient.get<SoftWareDeveloper>(url).pipe(
      catchError(this.handleError<SoftWareDeveloper>('getDeveloper'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    }
  }
}
