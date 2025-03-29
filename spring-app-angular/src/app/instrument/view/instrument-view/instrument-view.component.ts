import { Component, OnInit } from '@angular/core';
import { InstrumentService } from "../../service/instrument.service";
import {ActivatedRoute, Router} from '@angular/router';
import {NgIf} from '@angular/common';
import { InstrumentDetails } from '../../model/instrument-details';

@Component({
  selector: 'app-instrument-view',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './instrument-view.component.html'
})
export class InstrumentViewComponent implements OnInit {
  constructor(private service: InstrumentService, private route: ActivatedRoute, private router: Router) {
  }

  instrument: InstrumentDetails | undefined;

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.service.getInstrument(params['uuid'])
        .subscribe(instrument => this.instrument = instrument)
      }
    )
  }
}
