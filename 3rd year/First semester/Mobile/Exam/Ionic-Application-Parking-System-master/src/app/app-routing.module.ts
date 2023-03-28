import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'main-manager',
    loadChildren: () => import('./manager/main-manager/main-manager.module').then(m => m.MainManagerPageModule)
  },
  {
    path: 'main-stats',
    loadChildren: () => import('./stats/main-stats/main-stats.module').then(m => m.MainStatsPageModule)
  },
  {
    path: 'main-user',
    loadChildren: () => import('./user/main-user/main-user.module').then(m => m.MainUserPageModule)
  },
  {
    path: 'add-space',
    loadChildren: () => import('./manager/add-space/add-space.module').then(m => m.AddSpacePageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
