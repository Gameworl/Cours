import { Injectable } from '@angular/core';
import { Contact } from '../modele/Contact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  public listContact = new Array<Contact>();

  constructor() {
    if (!localStorage.getItem('contacts')){
      localStorage.setItem('contacts', JSON.stringify(this.listContact));
    }
    this.listContact = JSON.parse(localStorage.getItem('contacts'));
   }

 public addContact(contact : Contact): void{
    this.listContact.push(contact);
    localStorage.setItem('contacts' , JSON.stringify(this.listContact));
  }

  public deleteContact(contact : Contact): void{
    this.listContact = this.listContact.filter(c => c !== contact);
    localStorage.setItem('contacts', JSON.stringify(this.listContact));
  }

 public  editContact(contact: Contact, newContact: Contact): void {
    let index = this.listContact.indexOf(contact);
    this.listContact[index] = newContact;
    localStorage.setItem('contacts', JSON.stringify(this.listContact));
  }
}
