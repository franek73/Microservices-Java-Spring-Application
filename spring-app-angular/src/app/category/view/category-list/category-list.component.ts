import { Component, OnInit } from '@angular/core';
import { CategoryService } from "../../service/category.service";
import { Categories } from "../../model/categories";
import {RouterLink} from '@angular/router';
import {NgForOf, NgIf} from '@angular/common';
import {Category} from '../../model/category';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [
    RouterLink,
    NgForOf,
    NgIf
  ],
  templateUrl: './category-list.component.html'
})
export class CategoryListComponent implements OnInit {
  constructor(private service: CategoryService) {
  }

  categories: Categories | undefined;

  ngOnInit(): void {
    this.service.getCategories().subscribe(categories => this.categories = categories)
  }

  onDelete(category: Category): void {
    this.service.deleteCategory(category.uuid).subscribe(() => this.ngOnInit())
  }
}
