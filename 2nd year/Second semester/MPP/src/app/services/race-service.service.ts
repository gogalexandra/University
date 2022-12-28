import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { addRace } from '../dtos/race/addRace';
import { updateRace } from '../dtos/race/updateRace';
import { Race } from '../models/race';

@Injectable({
  providedIn: 'root'
})
export class RaceServiceService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private racesURL = 'http://localhost:8080/api/races';

  constructor(private httpClient: HttpClient) {
  }

  getRaces(): Observable<any> {
    return this.httpClient.get<Array<Race>>(this.racesURL).pipe(
      catchError(this.handleError<Race[]>('getRaces', []))
    );
  }

  update(race: updateRace): Observable<Race> {
    const url = `${this.racesURL}`;
    return this.httpClient.put<Race>(url, {id: race.id, location: race.location, date: race.date} as updateRace).pipe(
      catchError(this.handleError<Race>('updateRace'))
    );
  }

  add(race: addRace): Observable<any> {
    return this.httpClient.post<any>(this.racesURL, {location: race.location, date: race.date} as addRace).pipe(
      catchError(this.handleError<any>('addRace'))
    );
  }

  delete(raceId: number): Observable<any> {
    const url = `${this.racesURL}/${raceId}`;
    console.log(url);
    return this.httpClient.request('DELETE', url, this.httpOptions).pipe(
      catchError(this.handleError('removeRace'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    }
  }
}
