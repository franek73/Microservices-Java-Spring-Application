import { Component, OnInit } from '@angular/core';
import { CategoryService } from "../../service/category.service";
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {NgIf} from '@angular/common';
import {CategoryDetails} from '../../model/category-details';
import {InstrumentListComponent} from '../../../instrument/view/instrument-list/instrument-list.component';

@Component({
  selector: 'app-category-view',
  standalone: true,
  imports: [
    NgIf,
    RouterLink,
    InstrumentListComponent
  ],
  templateUrl: './category-view.component.html'
})
export class CategoryViewComponent implements OnInit {
  constructor(private service: CategoryService, private route: ActivatedRoute, private router: Router) {
  }

  category: CategoryDetails | undefined;

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.service.getCategory(params['uuid'])
        .subscribe(category => this.category = category)
      }
    )
  }
}
