import { Component, OnInit } from '@angular/core';
import { Guest } from './guest';
import { timer } from 'rxjs';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http'
import { environment } from 'src/environments/environment';
import { FormBuilder } from '@angular/forms';
import { AvailableRooms } from './availableRooms';
import { GuestHotel } from './guestHotel';


@Component({
  selector: 'app-guests',
  templateUrl: './guests.component.html',
  styleUrls: ['./guests.component.css'],
})
export class GuestsComponent implements OnInit {
  guests: Guest[];
  foundGuests: Guest[];
  availableRooms: AvailableRooms[];
  guestsHotels: GuestHotel[];


  searchForm = this.formBuilder.group({
    value: ''
  });

  addForm = this.formBuilder.group({
    name: '',
    surname: '',
    age: 0,
    arrivalDate: Date,
    departureDate: Date,
    roomNumber: 0
  });

  updateForm = this.formBuilder.group({
    guestId: 0,
    roomNumber: 0,
    departureDate: Date
  });

  deleteForm = this.formBuilder.group({
    guestId: 0
  });

  constructor(private http: HttpClient, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getAllGuests();
    this.getAvailableRooms();
    this.reorgGuests();
    this.getGuestsHotels();
  }

  refresh(): void {
    window.location.reload();
  }

  public getAllGuests() {
    this.http.get<Guest[]>(`${environment.apiBaseUrl}/guests/all`).subscribe(data => {
      this.guests = [...data];
    });
  }

  public getAvailableRooms() {
    this.http.get<AvailableRooms[]>(`${environment.apiBaseUrl}/rooms/available?status=available`).subscribe(data => {
      this.availableRooms = [...data];
    });
  }

  public reorgGuests() {
    if (this.guests === undefined)
      this.http.delete<string>(`${environment.apiBaseUrl}/guests/reorg`).subscribe();
  }

  public findGuest() {
    let search = this.searchForm.value.value;
    console.log("findGuest(" + search + ")");
    this.foundGuests = [];
    this.guests.forEach((guest) => {
      if (guest.age == search || guest.arrivalDate == search || guest.departureDate == search || guest.name.includes(search) || guest.surname.includes(search) || guest.guestId == search)
        this.foundGuests.push(guest);
    });
  }

  public addGuest() {
    console.log('Guest added');
    console.log(this.addForm.value);

    const params = new HttpParams()
      .set('name', this.addForm.value.name)
      .set('surname', this.addForm.value.surname)
      .set('age', this.addForm.value.age)
      .set('roomNumber', this.addForm.value.roomNumber.roomNumber)
      .set('departureDate', this.addForm.value.departureDate);

    console.log(`${environment.apiBaseUrl}/guests/add?${params}`)

    this.http.post<string>(`${environment.apiBaseUrl}/guests/add?${params}`, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }).subscribe(response => console.log(response));

    timer(500).subscribe(x => this.refresh());
  }

  public updateGuest() {
    console.log('Guest updated');
    console.log(this.updateForm.value);

    const params = new HttpParams()
      .set('guestId', this.updateForm.value.guestId)
      .set('roomNumber', this.updateForm.value.roomNumber.roomNumber)
      .set('departureDate', this.updateForm.value.departureDate);

    this.http.put<string>(`${environment.apiBaseUrl}/guests/update?${params}`, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }).subscribe(response => console.log(response));

    timer(500).subscribe(x => this.refresh());
  }

  public deleteGuest() {
    console.log('Guest removed');
    console.log(this.deleteForm.value);

    const params = new HttpParams()
      .set('id', this.deleteForm.value.guestId)

    this.http.delete<string>(`${environment.apiBaseUrl}/guests/delete?${params}`, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }).subscribe(response => console.log(response));

    timer(500).subscribe(x => this.refresh());
  }

  public getGuestsHotels() {
    this.http.get<GuestHotel[]>(`${environment.apiBaseUrl}/guests/guestsHotels`).subscribe(data => {
      this.guestsHotels = [...data];
    })
  }

}
