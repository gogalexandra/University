import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {Race} from "../../models/race-model";
import {updateRace} from "../../dto/races/updateRace";
import {addRace} from "../../dto/races/addRace";
import {Team} from "../../models/team-model";
import {updateTeam} from "../../dto/teams/updateTeam";
import {addTeam} from "../../dto/teams/addTeam";
import {AddDriverToTeam} from "../../dto/teams/addDriverToTeam";
import {addDriver} from "../../dto/drivers/addDriver";

@Injectable({
  providedIn: 'root'
})
export class TeamsService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private teamsURL = 'http://localhost:8080/api/teams';

  constructor(private httpClient: HttpClient) {
  }

  public getTeams(): Observable<any> {
    return this.httpClient.get<Array<Team>>(this.teamsURL).pipe(
      catchError(this.handleError<Race[]>('getTeams', []))
    );
  }

  public update(team: Team): Observable<Race> {
    const url = `${this.teamsURL}/${team.id}`;
    return this.httpClient.put<Team>(url, {name: team.name} as updateTeam).pipe(
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

  public addDriverToTeam(teamId: number, addDriverToTeam: AddDriverToTeam): Observable<any>{
    const url = `${this.teamsURL}/${teamId}/drivers`;
    return this.httpClient.post<any>(url, {id: addDriverToTeam.id} as AddDriverToTeam).pipe(
      catchError(this.handleError('addDriverToTeam'))
    )
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    }
  }

}
