import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule ,ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FormContactPageRoutingModule } from './form-contact-routing.module';

import { FormContactPage } from './form-contact.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FormContactPageRoutingModule,
    ReactiveFormsModule

  ],
  declarations: [FormContactPage]
})
export class FormContactPageModule {}
