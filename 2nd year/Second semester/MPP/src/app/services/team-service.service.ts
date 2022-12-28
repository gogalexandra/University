import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { AddDriverToTeam } from '../dtos/team/addDriverToTeam';
import { addTeam } from '../dtos/team/addTeam';
import { updateTeam } from '../dtos/team/updateTeam';
import { Race } from '../models/race';
import { Team } from '../models/team';

@Injectable({
  providedIn: 'root'
})
export class TeamServiceService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private teamsURL = 'http://localhost:8080/api/teams';

  constructor(private httpClient: HttpClient) {
  }

  public getTeams(): Observable<any> {
    return this.httpClient.get<Array<Team>>(this.teamsURL).pipe(
      catchError(this.handleError<Team[]>('getTeams', []))
    );
  }

  public update(team: updateTeam): Observable<Team> {
    const url = `${this.teamsURL}`;
    return this.httpClient.put<Team>(url, {id: team.id, name: team.name} as updateTeam).pipe(
      catchError(this.handleError<Team>('updateTeam'))
    );
  }

  public add(team: addTeam): Observable<any> {
    return this.httpClient.post<any>(this.teamsURL, {name: team.name} as addTeam).pipe(
      catchError(this.handleError<any>('addTeam'))
    );
  }

  public delete(teamId: number): Observable<any> {
    const url = `${this.teamsURL}/${teamId}`;
    return this.httpClient.request('DELETE', url, this.httpOptions).pipe(
      catchError(this.handleError('removeTeam'))
    );
  }

  public addDriverToTeam(teamId: number, driverId: number): Observable<any>{
    const params = {params: new HttpParams({fromString:`teamId=${teamId}&driverId=${driverId}`})} 
    const url = `${this.teamsURL}/add`;
    return this.httpClient.post<any>(url, params).pipe(
      catchError(this.handleError('addDriverToTeam'))
    )
  }

  public deleteDriverFromTeam(teamId: number, driverId: number): Observable<any>{
    const queryParams = new HttpParams()
      .set('teamId', "teamId")
      .set('driverId', driverId);
    const url = `${this.teamsURL}/remove?teamId=${teamId}&${driverId}`;
    console.log(queryParams)
    return this.httpClient.post<any>(url,null ).pipe(
      catchError(this.handleError('deleteDriverFromTeam'))
    )
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    }
  }
}
