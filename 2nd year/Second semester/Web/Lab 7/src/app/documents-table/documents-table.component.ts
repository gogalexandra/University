import { Component, OnInit } from '@angular/core';
import { DocumentsService } from '../Services/documents-service/documents-service';
import { documentResponse } from '../model/documentResponse';
import { DocumentMapped} from '../model/documentMapped';
import { MatRow } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DocumentFormComponent } from '../document-form/document-form.component';

@Component({
  selector: 'app-documents-table',
  templateUrl: './documents-table.component.html',
  styleUrls: ['./documents-table.component.scss']
})
export class DocumentsTableComponent implements OnInit {

  documents: Array<DocumentMapped> = [];
  displayedColumns: string[] = ['id', 'title', 'author', 'noOfPages', 'type', 'format', 'action'];
  clickedRows: Array<DocumentMapped> = [];


  ngOnInit(): void {
  }

  constructor(private documentsService: DocumentsService, public dialog: MatDialog) { 
    this.subscribeToDocuments();
  }

  public subscribeToDocuments(): void{
    this.documentsService.fetchDocuments().subscribe((documents) => {
      this.documents = documents;
    });
  }

  public changeSelectedRow(selectedRow: DocumentMapped): void{
    this.clickedRows = [];
    this.clickedRows.push(selectedRow)
  }

  public deleteDocument(id: number): void{
    this.documentsService.deleteDocument(id? id: 0);
    this.subscribeToDocuments();
  }

  public openDialog(): void{
    const dialogRef = this.dialog.open(DocumentFormComponent, {
      width: "100%",
      autoFocus: true,
      data: undefined
    });

    dialogRef.afterClosed().subscribe(result => {
      this.documentsService.addDocument(result);
      this.subscribeToDocuments();
    });
  }

  public openDialog2(document: DocumentMapped): void{
    const dialogRef = this.dialog.open(DocumentFormComponent, {
      width: "100%",
      autoFocus: true,
      data:  {id: document.id, title: document.title, author: document.author, noOfPages: document.noOfPages, type: document.type, format: document.format}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.documentsService.updateDocument(result);
      this.subscribeToDocuments();

    });
  }


}
