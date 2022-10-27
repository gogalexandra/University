import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {Driver} from "../../models/driver-model";
import {updateDriver} from "../../dto/drivers/updateDriver";
import {addDriver} from "../../dto/drivers/addDriver";


@Injectable({
  providedIn: 'root'
})
export class DriversService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private driversURL = 'http://localhost:8080/api/drivers';

  constructor(private httpClient: HttpClient) {
  }

  getDrivers(): Observable<any> {
    return this.httpClient.get<Array<Driver>>(this.driversURL).pipe(
      catchError(this.handleError<Driver[]>('getDrivers', []))
    );
  }

  update(driver: Driver): Observable<Driver> {
    const url = `${this.driversURL}/${driver.id}`;
    return this.httpClient.put<Driver>(url, {name: driver.name} as updateDriver).pipe(
      catchError(this.handleError<Driver>('updateDriver'))
    );
  }

  add(driver: addDriver): Observable<any> {
    return this.httpClient.post<any>(this.driversURL, {name: driver.name} as addDriver).pipe(
      catchError(this.handleError<any>('addDriver'))
    );
  }

  delete(driverId: number): Observable<any> {
    const url = `${this.driversURL}/${driverId}`;
    console.log(url);
    return this.httpClient.request('DELETE', url, this.httpOptions).pipe(
      catchError(this.handleError('removeDriver'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    }
  }

}
