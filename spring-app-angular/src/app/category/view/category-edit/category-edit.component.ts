import { Component, OnInit } from '@angular/core';
import { CategoryService } from "../../service/category.service";
import {ActivatedRoute, Router} from '@angular/router';
import {CategoryForm} from '../../model/category-form';
import {FormsModule} from '@angular/forms';
import { v4 as uuidv4 } from 'uuid';
import {catchError, of} from 'rxjs';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-category-edit',
  standalone: true,
  imports: [
    NgIf,
    FormsModule
  ],
  templateUrl: './category-edit.component.html'
})
export class CategoryEditComponent implements OnInit {
  constructor( private categoryService: CategoryService,
               private route: ActivatedRoute,
               private router: Router
  ) {
  }

  uuid: string | undefined

  category: CategoryForm | undefined

  original: CategoryForm | undefined

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['uuid']) {
        this.categoryService.getCategory(params['uuid']).subscribe(category => {
          this.uuid = category.uuid;
          this.category = {
            name: category.name,
            description: category.description
          };
          this.original = { ...this.category };
        });
      } else {
        const generateUniqueUuid = () => {
          const newUuid = uuidv4();
          this.categoryService.getCategory(newUuid).pipe(
            catchError(() => {
              return of(null);
            })
          ).subscribe(category => {
            if (category) {
              generateUniqueUuid();
            } else {
              this.uuid = newUuid;
            }
          });
        };

        generateUniqueUuid();
        this.category = {
          name: '',
          description: ''
        };
        this.original = { ...this.category };
      }
    });
  }

  onSubmit(): void {
    this.categoryService.putCategory(this.uuid!, this.category!)
      .subscribe(() => this.router.navigate(['/categories']))
  }
}
