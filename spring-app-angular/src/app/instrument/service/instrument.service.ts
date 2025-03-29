import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {Observable, tap} from "rxjs";
import { Instruments } from "../model/instruments";
import {InstrumentDetails} from '../model/instrument-details';
import {InstrumentForm} from '../model/instrument-form';

@Injectable({
  providedIn: 'root'
})
export class InstrumentService{
  constructor(private http: HttpClient) {}

  getInstruments(): Observable<Instruments>{
    return this.http.get<Instruments>('/api/instruments')
  }

  getInstrument(uuid: string): Observable<InstrumentDetails>{
    return this.http.get<InstrumentDetails>('/api/instruments/' + uuid)
  }

  getInstrumentsByCategory(categoryUuid: string): Observable<Instruments> {
    return this.http.get<Instruments>('/api/instruments/category/' + categoryUuid);
  }

  deleteInstrument(uuid: string): Observable<any>{
    return this.http.delete('/api/instruments/' + uuid)
  }

  putInstrument(uuid: string, request: InstrumentForm): Observable<any> {
    const url = `/api/instruments/${uuid}`;
    console.log('PUT Request URL:', url);
    console.log('Request Body:', request);

    return this.http.put(url, request).pipe(
      tap(() => console.log(`PUT request sent to: ${url}`, request))
    );
  }
}


