import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProjectComponent } from './component/project/project.component';
import { HttpClientModule } from '@angular/common/http';
import { MyProjectsComponent } from './component/my-projects/my-projects.component';
import { AddDeveloperComponent } from './component/add-developer/add-developer.component';


@NgModule({
  declarations: [
    AppComponent,
    ProjectComponent,
    MyProjectsComponent,
    AddDeveloperComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
