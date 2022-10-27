import {NgModule} from '@angular/core';
import {DriversComponent} from "./components/drivers/drivers.component";
import {RouterModule, Routes} from "@angular/router";
import {ParticipationsComponent} from "./components/participations/participations.component";
import {RacesComponent} from "./components/races/races.component";
import {TeamsComponent} from "./components/team/teams.component";

const routes: Routes = [
  {path: 'drivers', component: DriversComponent},
  {path: 'races', component: RacesComponent},
  {path: 'teams', component: TeamsComponent},
  {path: 'participations', component: ParticipationsComponent},
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
