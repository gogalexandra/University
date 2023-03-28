import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private http: HttpClient) { }

  public getAllElements() {
    return this.http.get('http://localhost:2021/spaces');
  }

  public getFreeElements() {
    return this.http.get('http://localhost:2021/free');
  }

  public addElement(elem: any): Observable<object> {
    return this.http.post('http://localhost:2021/space', elem);
  }

  public reserveElement(id: any): Observable<object> {
    // TODO: check if it has to be string, number, etc
    return this.http.post('http://localhost:2021/take', { id: id });
  }

  // public updateElement(elem: any): Observable<object> {
  //   return this.http.put('http://localhost:8080/courses/' + elem.id, elem);
  // }

  public deleteElement(id: any): Observable<object> {
    return this.http.delete('http://localhost:2021/space/' + id);
  }
}
