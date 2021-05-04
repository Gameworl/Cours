import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FormContactPage } from './form-contact.page';

const routes: Routes = [
  {
    path: '',
    component: FormContactPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FormContactPageRoutingModule {}
