import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddDeveloperComponent } from './component/add-developer/add-developer.component';
import { MyProjectsComponent } from './component/my-projects/my-projects.component';
import { ProjectComponent } from './component/project/project.component';

const routes: Routes = [
  {path: 'projects', component: ProjectComponent},
  {path: 'myprojects', component: MyProjectsComponent},
  {path: 'developer', component: AddDeveloperComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
