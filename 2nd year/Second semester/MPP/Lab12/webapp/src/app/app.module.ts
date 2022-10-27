import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { DriversComponent } from './components/drivers/drivers.component';
import {RouterModule} from "@angular/router";
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ParticipationsComponent} from "./components/participations/participations.component";
import {RacesComponent} from "./components/races/races.component";
import {TeamsComponent} from "./components/team/teams.component";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    DriversComponent,
    ParticipationsComponent,
    RacesComponent,
    TeamsComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
