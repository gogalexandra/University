import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DriverComponent } from './components/driver/driver/driver.component';
import { ParticipationComponent } from './components/participation/participation/participation.component';
import { RaceComponent } from './components/race/race/race.component';
import { TeamComponent } from './components/team/team/team.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddDriverComponent } from './components/add-driver/add-driver.component';



@NgModule({
  declarations: [
    AppComponent,
    DriverComponent,
    ParticipationComponent,
    RaceComponent,
    TeamComponent,
    AddDriverComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    MatDialogModule,
    MatFormFieldModule,
    BrowserAnimationsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
