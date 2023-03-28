import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AddSpacePageRoutingModule } from './add-space-routing.module';

import { AddSpacePage } from './add-space.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AddSpacePageRoutingModule
  ],
  declarations: [AddSpacePage]
})
export class AddSpacePageModule {}
