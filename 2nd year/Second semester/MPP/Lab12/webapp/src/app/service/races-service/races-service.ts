import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {Race} from "../../models/race-model";
import {updateRace} from "../../dto/races/updateRace";
import {addRace} from "../../dto/races/addRace";

@Injectable({
  providedIn: 'root'
})
export class RacesService {
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

  update(race: Race): Observable<Race> {
    const url = `${this.racesURL}/${race.id}`;
    return this.httpClient.put<Race>(url, {name: race.name} as updateRace).pipe(
      catchError(this.handleError<Race>('updateRace'))
    );
  }

  add(race: addRace): Observable<any> {
    return this.httpClient.post<any>(this.racesURL, {name: race.name} as addRace).pipe(
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
