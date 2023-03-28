import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { MainManagerPageRoutingModule } from './main-manager-routing.module';

import { MainManagerPage } from './main-manager.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    MainManagerPageRoutingModule
  ],
  declarations: [MainManagerPage]
})
export class MainManagerPageModule {}
