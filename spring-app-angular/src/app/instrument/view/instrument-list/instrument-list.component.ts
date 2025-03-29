import {Component, Input, OnInit} from '@angular/core';
import { InstrumentService } from "../../service/instrument.service";
import { Instruments } from "../../model/instruments";
import {RouterLink} from '@angular/router';
import {NgForOf, NgIf} from '@angular/common';
import {Instrument} from '../../model/instrument';

@Component({
  selector: 'app-instrument-list',
  standalone: true,
  imports: [
    RouterLink,
    NgForOf,
    NgIf
  ],
  templateUrl: './instrument-list.component.html'
})
export class InstrumentListComponent implements OnInit {
  @Input() categoryUuid!: string;
  constructor(private service: InstrumentService) {
  }

  instruments: Instruments | undefined;

  ngOnInit(): void {
    if (this.categoryUuid) {
      this.service.getInstrumentsByCategory(this.categoryUuid)
        .subscribe(instruments => this.instruments = instruments);
    }
  }

  onDelete(instrument: Instrument): void {
    this.service.deleteInstrument(instrument.uuid).subscribe(() => this.ngOnInit())
  }
}
