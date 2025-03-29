import { Component, OnInit } from '@angular/core';
import { InstrumentService } from "../../service/instrument.service";
import {ActivatedRoute, Router} from '@angular/router';
import {InstrumentForm} from '../../model/instrument-form';
import {FormsModule} from '@angular/forms';
import { v4 as uuidv4 } from 'uuid';
import {catchError, of} from 'rxjs';
import {NgIf} from '@angular/common';
import {CategoryService} from '../../../category/service/category.service';
import {Categories} from '../../../category/model/categories';

@Component({
  selector: 'app-instrument-edit',
  standalone: true,
  imports: [
    NgIf,
    FormsModule
  ],
  templateUrl: './instrument-edit.component.html'
})
export class InstrumentEditComponent implements OnInit {
  constructor( private instrumentService: InstrumentService,
               private categoryService: CategoryService,
               private route: ActivatedRoute,
               private router: Router
  ) {
  }

  uuid: string | undefined

  instrument: InstrumentForm | undefined

  original: InstrumentForm | undefined

  categories: Categories | undefined

  categoryUuid: string | undefined;

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.categoryUuid = params['categoryUuid'];
    });

    this.route.params.subscribe(params => {
      if (params['uuid']) {
        this.categoryService.getCategories()
          .subscribe(categories => this.categories = categories);

        this.instrumentService.getInstrument(params['uuid']).subscribe(instrument => {
          this.uuid = instrument.uuid;
          this.instrument = {
            name: instrument.name,
            description: instrument.description,
            category: instrument.category.uuid
          };
          this.original = { ...this.instrument };
        });
      } else {
        const generateUniqueUuid = () => {
          const newUuid = uuidv4();
          this.instrumentService.getInstrument(newUuid).pipe(
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
        this.instrument = {
          name: '',
          description: '',
          category: this.categoryUuid || ''
        };
        this.original = { ...this.instrument };
      }
    });
  }

  onSubmit(): void {
    this.instrumentService.putInstrument(this.uuid!, this.instrument!)
      .subscribe(() => this.router.navigate(['/instruments']))
  }
}
