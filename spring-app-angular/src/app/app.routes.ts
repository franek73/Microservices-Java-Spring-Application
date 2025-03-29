import { Routes } from '@angular/router';
import {CategoryEditComponent} from './category/view/category-edit/category-edit.component';
import {CategoryListComponent} from './category/view/category-list/category-list.component';
import {CategoryViewComponent} from './category/view/category-view/category-view.component';
import {InstrumentEditComponent} from './instrument/view/instrument-edit/instrument-edit.component';
import {InstrumentViewComponent} from './instrument/view/instrument-view/instrument-view.component';
import {InstrumentListComponent} from './instrument/view/instrument-list/instrument-list.component';

export const routes: Routes = [{
  component: CategoryEditComponent,
  path: "categories/:uuid/edit"
  },
  {
    component: CategoryListComponent,
    path: "categories"
  },
  {
    component: CategoryViewComponent,
    path: "categories/:uuid"
  },
  {
    component: InstrumentEditComponent,
    path: "instruments/:uuid/edit"
  },
  {
  component: InstrumentListComponent,
  path: "categories/:uuid/instruments"
  },
  {
    component: InstrumentViewComponent,
    path: "instruments/:uuid"
  },
  {
    component: InstrumentListComponent,
    path: "instruments"
  }

];
