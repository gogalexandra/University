import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { documentResponse } from 'src/app/model/documentResponse';


import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { DocumentMapped } from 'src/app/model/documentMapped';

@Injectable({
    providedIn: 'root'
})
export class DocumentsService {
    private backendUrl = 'http://localhost/lab';

    documents: BehaviorSubject<Array<DocumentMapped>> = new BehaviorSubject(new Array());

    constructor(private http: HttpClient) { }

    fetchDocuments(): Observable<Array<DocumentMapped>> {
        return this.http.get<Array<DocumentMapped>>(this.backendUrl + '/get.php')
    }

    addDocument(data: any) {
        const body = JSON.stringify({
            "title": data.title,
            "author": data.author,
            "noOfPages": data.noOfPages,
            "type": data.type,
            "format": data.format});
        console.log(body);
    
        return this.http.post(this.backendUrl + '/add.php', body).subscribe(data => {
          console.log(data);
        });;
      }

    deleteDocument(id: number) {
        const body = JSON.stringify({ id });
        return this.http.post(this.backendUrl + '/delete.php', body).subscribe(data => {
            console.log(data);
        });
    }

    updateDocument(data: DocumentMapped) {
        const headers = {
            'content-type' : 'application/json',
            'Access-Control-Allow-Origin':'*',
          };
        const body = JSON.stringify({
            "id":data.id,
            "title":data.title,
            "author":data.author,
            "noOfPages":data.noOfPages,
            "type":data.type,
            "format":data.format
         });
    
        return this.http.post(this.backendUrl + '/update.php', body, {'headers': headers}).subscribe(data => {
          console.log(data);
        });
    }
}
