import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DocumentsTableComponent } from './documents-table/documents-table.component';
import { DocumentsService } from './Services/documents-service/documents-service';

const routes: Routes = [
  {
    path: 'documents', component: DocumentsTableComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
