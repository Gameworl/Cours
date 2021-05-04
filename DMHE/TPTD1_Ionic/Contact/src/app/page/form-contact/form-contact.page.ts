import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Contact } from 'src/app/modele/Contact';
import { ContactService } from 'src/app/service/contact.service';

@Component({
  selector: 'app-form-contact',
  templateUrl: './form-contact.page.html',
  styleUrls: ['./form-contact.page.scss'],
})
export class FormContactPage implements OnInit {
  public contactForm: FormGroup;
  contact: Contact;
  existant: boolean = false;
  newContact : Contact;
  constructor(public formBuilder: FormBuilder, public contactService: ContactService, private route: ActivatedRoute, private router: Router) {
    this.route.queryParams.subscribe(params => {
      if (this.router.getCurrentNavigation().extras.state) {
        this.contact = this.router.getCurrentNavigation().extras.state.contactSend;
      }
      this.contactForm = new FormGroup({
        nom : new FormControl(this.contact === undefined ? '' : this.contact.nom, [Validators.required, Validators.minLength(2), Validators.pattern('^[a-zA-Z]+$')]),
        prenom: new FormControl(this.contact === undefined ? '' : this.contact.prenom, [Validators.required, Validators.minLength(2), Validators.pattern('^[a-zA-Z]+$')]),
        naissance: new FormControl(this.contact === undefined ? '' : this.contact.naissance, [Validators.required, Validators.minLength(8), Validators.pattern('^[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]$')]),
        numero: new FormControl(this.contact === undefined ? '' : this.contact.numero, [Validators.required, Validators.minLength(10), Validators.maxLength(10),Validators.pattern('^[0][0-9]{9}$')]),
        email: new FormControl(this.contact === undefined ? '' : this.contact.email, [Validators.required, Validators.email, Validators.minLength(4)]),
        description: new FormControl(this.contact === undefined ? '' : this.contact.description, [Validators.required]),
      });
      this.existant = this.contact === undefined ? false : true;
    });
   }

  ngOnInit() {
  }

  onCreate() {
    if(this.contactForm.valid){
      this.contactService.addContact(this.contactForm.value)
    }
  }

  onEdit(){
    if(this.contactForm.valid){
      this.contactService.editContact(this.contact, this.contactForm.value);
    }
  }
}
