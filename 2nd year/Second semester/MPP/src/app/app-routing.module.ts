import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddDriverComponent } from './components/add-driver/add-driver.component';
import { DriverComponent } from './components/driver/driver/driver.component';
import { ParticipationComponent } from './components/participation/participation/participation.component';
import { RaceComponent } from './components/race/race/race.component';
import { TeamComponent } from './components/team/team/team.component';

const routes: Routes = [
  {path: 'drivers', component: DriverComponent},
  {path: 'races', component: RaceComponent},
  {path: 'teams', component: TeamComponent},
  {path: 'participations', component: ParticipationComponent},
  {path: 'operations', component: AddDriverComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
