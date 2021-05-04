import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Contact } from 'src/app/modele/Contact';
import { ContactService } from 'src/app/service/contact.service';
import {ActivatedRoute} from '@angular/router';
@Component({
  selector: 'app-card-contact',
  templateUrl: './card-contact.component.html',
  styleUrls: ['./card-contact.component.scss'],
})
export class CardContactComponent implements OnInit {
  @Input('data') contact : Contact;
  @Output() edit : EventEmitter<Contact> = new EventEmitter<Contact>();
  @Output() delete : EventEmitter<Contact> = new EventEmitter<Contact>();

  constructor() { }

  ngOnInit() {
  }
}
