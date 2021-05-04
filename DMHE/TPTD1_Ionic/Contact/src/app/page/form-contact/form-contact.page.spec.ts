import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { FormContactPage } from './form-contact.page';

describe('FormContactPage', () => {
  let component: FormContactPage;
  let fixture: ComponentFixture<FormContactPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormContactPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(FormContactPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
