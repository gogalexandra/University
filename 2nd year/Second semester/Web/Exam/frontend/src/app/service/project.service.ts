import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { AddProjectDTO } from '../dto/AddProjectDTO';
import { Project } from '../models/Project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private projectURL = 'http://localhost:8080/api/projects';

  constructor(private httpClient: HttpClient) {
  }

  public getProjects(): Observable<any> {
    return this.httpClient.get<Array<Project>>(this.projectURL).pipe(
      catchError(this.handleError<Project[]>('getProjects', []))
    );
  }

  public getProjectsForDeveloper(name: string): Observable<any> {
    const url = `${this.projectURL}/${name}`;
    return this.httpClient.get<Array<Project>>(url).pipe(
      catchError(this.handleError<Project[]>('getProjects', []))
    );
  }

  public getProjectsByName(name: string): Observable<any> {
    const url = `${this.projectURL}/get/${name}`;
    return this.httpClient.get<Project>(url).pipe(
      catchError(this.handleError<Project>('getProjectsbyName'))
    );
  }

  public add(project: AddProjectDTO): Observable<any> {
    return this.httpClient.post<any>(this.projectURL, {ProjectManagerID: project.ProjectManagerID, name: project.name, description: project.description, members: project.members} as AddProjectDTO).pipe(
      catchError(this.handleError<any>('addProject'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    }
  }
}
