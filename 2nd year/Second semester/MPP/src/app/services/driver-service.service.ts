import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError, map} from "rxjs/operators";
import { Driver } from '../models/driver';
import { addDriver } from '../dtos/driver/addDriver';
import { updateDriver } from '../dtos/driver/updateDriver';

@Injectable({
  providedIn: 'root'
})
export class DriverServiceService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private driversURL = 'http://localhost:8080/api/drivers';

  constructor(private httpClient: HttpClient) {
  }

  public getDrivers(): Observable<any> {
    return this.httpClient.get<Array<Driver>>(this.driversURL).pipe(
      catchError(this.handleError<Driver[]>('getDrivers', []))
    );
  }

  public getDriverByName(driverName: String): Observable<Driver> {
    return this.getDrivers().pipe(
      map((response: { drivers: any[]; }) => response.drivers.find(driver => driver.name === driver))
    );
  }

  public update(driver: updateDriver): Observable<Driver> {
    const url = `${this.driversURL}`;
    return this.httpClient.put<Driver>(url, {name: driver.name, experience: driver.experience, team:driver.team} as updateDriver).pipe(
      catchError(this.handleError<Driver>('updateDriver'))
    );
  }

  public add(driver: addDriver): Observable<any> {
    return this.httpClient.post<any>(this.driversURL, {name: driver.name, experience: driver.experience, team:driver.team} as addDriver).pipe(
      catchError(this.handleError<any>('addDriver'))
    );
  }

  public delete(driverId: number): Observable<any> {
    const url = `${this.driversURL}/${driverId}`;
    console.log(url);
    return this.httpClient.request('DELETE', url, this.httpOptions).pipe(
      catchError(this.handleError('removeDriver'))
    );
  }

  public getDriversOfTeam(teamId: number): Observable<any> {
    const url = `${this.driversURL}/team/${teamId}`;
    return this.httpClient.get<Array<Driver>>(url).pipe(
      catchError(this.handleError<Driver[]>('getDriversOfTeam', []))
    );
  }


  //must check
  // public deleteDriverFromTeam(driverId: number): Observable<any>{
  //   const url = `${this.driversURL}/${driverId}/removeFromTeam`;
  //   return this.httpClient.post<any>(url, "").pipe(
  //     catchError(this.handleError('removeFromTeam'))
  //   )
  // }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    }
  }
}
