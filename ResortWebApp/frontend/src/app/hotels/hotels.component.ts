import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { FormBuilder } from '@angular/forms';
import { HotelActivity } from './hotelActivity';
import { AveragePricePerHotel } from './averagePricePerHotel';
import { HotelCapacity } from './hotelCapacity';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {
  hotelsActivities: HotelActivity[];
  averagePricesPerHotel: AveragePricePerHotel[];
  hotelCapacities: HotelCapacity[];

  constructor(private http: HttpClient, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getHotelActivities();
    this.getAveragePricePerHotel();
    this.getHotelCapacities();
  }

  getHotelActivities() {
    this.http.get<HotelActivity[]>(`${environment.apiBaseUrl}/hotels/hotelActivities`).subscribe(data => {
      this.hotelsActivities = [...data];
    })
  }

  getAveragePricePerHotel() {
    this.http.get<AveragePricePerHotel[]>(`${environment.apiBaseUrl}/hotels/averagePriceByHotel`).subscribe(data => {
      this.averagePricesPerHotel = [...data];
    })
  }

  getHotelCapacities() {
    this.http.get<HotelCapacity[]>(`${environment.apiBaseUrl}/hotels/hotelCapacity`).subscribe(data => {
      this.hotelCapacities = [...data];
    })
  }

}
