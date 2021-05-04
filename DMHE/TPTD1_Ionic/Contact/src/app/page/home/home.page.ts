import { Component } from '@angular/core';
import { Contact } from 'src/app/modele/Contact';
import { ContactService } from 'src/app/service/contact.service';
import { NavigationExtras, Router} from '@angular/router';
@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  constructor(public contactService : ContactService,private navig: Router) {}
  ngOnInit() {
  }

  catchEdition(contact : Contact){
    let navigationExtras: NavigationExtras = {
      state: {
        contactSend : contact
      }
    };
    this.navig.navigate(['/form-contact'], navigationExtras);
  }

  catchDelete(contact : Contact){
    this.contactService.deleteContact(contact);
  }
}
