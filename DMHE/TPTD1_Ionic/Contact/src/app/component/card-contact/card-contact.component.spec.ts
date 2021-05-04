import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { CardContactComponent } from './card-contact.component';

describe('CardContactComponent', () => {
  let component: CardContactComponent;
  let fixture: ComponentFixture<CardContactComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardContactComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(CardContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
