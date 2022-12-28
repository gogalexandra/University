import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { addParticipation } from '../dtos/participation/addParticipation';
import { Participation } from '../models/participation';

@Injectable({
  providedIn: 'root'
})
export class ParticipationServiceService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private participationsURL = 'http://localhost:8080/api/participations';

  constructor(private httpClient: HttpClient) {
  }

  getParticipations(): Observable<any> {
    return this.httpClient.get<Array<Participation>>(this.participationsURL).pipe(
      catchError(this.handleError<Participation[]>('getParticipations', []))
    );
  }

  add(participation: addParticipation): Observable<any> {
    return this.httpClient.post<any>(this.participationsURL, {
      teamId: participation.teamId,
      raceId: participation.raceId,
    } as addParticipation).pipe(
      catchError(this.handleError<any>('addParticipation'))
    );
  }

  delete(participationId: number): Observable<any> {
    const url = `${this.participationsURL}/${participationId}`;
    console.log(url);
    return this.httpClient.request('DELETE', url, this.httpOptions).pipe(
      catchError(this.handleError('removeParticipation'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    }
  }
}
