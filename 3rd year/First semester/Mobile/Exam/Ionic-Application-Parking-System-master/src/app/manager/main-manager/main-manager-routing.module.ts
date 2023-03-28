import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MainManagerPage } from './main-manager.page';

const routes: Routes = [
  {
    path: '',
    component: MainManagerPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainManagerPageRoutingModule {}
