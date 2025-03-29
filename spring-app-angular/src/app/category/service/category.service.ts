import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Categories } from "../model/categories";
import {CategoryDetails} from '../model/category-details';
import {CategoryForm} from '../model/category-form';

@Injectable({
  providedIn: 'root'
})
export class CategoryService{
  constructor(private http: HttpClient) {}

  getCategories(): Observable<Categories>{
    return this.http.get<Categories>('/api/categories')
  }

  getCategory(uuid: string): Observable<CategoryDetails>{
    return this.http.get<CategoryDetails>('api/categories/' + uuid)
  }

  deleteCategory(uuid: string): Observable<any>{
    return this.http.delete('/api/categories/' + uuid)
  }

  putCategory(uuid: string, request: CategoryForm): Observable<any>{
    return this.http.put('/api/categories/' + uuid, request)
  }
}


