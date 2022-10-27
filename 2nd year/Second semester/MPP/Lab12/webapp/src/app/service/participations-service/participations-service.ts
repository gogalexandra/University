import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {Participation} from "../../models/participation-model";
import {addParticipation} from "../../dto/participations/addParticipation";

@Injectable({
  providedIn: 'root'
})
export class ParticipationsService {
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
      points: participation.points
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
