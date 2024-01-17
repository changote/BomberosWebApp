import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaReporteComponent } from './lista-reporte.component';

describe('ListaReporteComponent', () => {
  let component: ListaReporteComponent;
  let fixture: ComponentFixture<ListaReporteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListaReporteComponent]
    });
    fixture = TestBed.createComponent(ListaReporteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
