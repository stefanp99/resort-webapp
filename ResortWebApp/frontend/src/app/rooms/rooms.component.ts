import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http'
import { environment } from 'src/environments/environment';
import { FormBuilder } from '@angular/forms';
import { Room } from './room';
import { AvailableRooms } from '../guests/availableRooms';
import { Guest } from '../guests/guest';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {
  occupiedRooms: AvailableRooms[];
  guests: Guest[];
  rooms: Room[];
  foundRooms: Room[];
  nrOccupiedRooms: number

  occupiedRoomsForm = this.formBuilder.group({
    roomNumber: 0
  })

  roomsForm = this.formBuilder.group({
    roomNumber: 0,
  })

  constructor(private http: HttpClient, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getRooms();
    this.getOccupiedRooms();
    this.getNumberOfOccupiedRooms();
  }

  refresh(): void {
    window.location.reload();
  }


  public getRooms() {
    this.http.get<Room[]>(`${environment.apiBaseUrl}/rooms/all`).subscribe(data => {
      this.rooms = [...data];
    })
  }

  public getOccupiedRooms() {
    const params = new HttpParams()
      .set('status', 'occupied');
    this.http.get<AvailableRooms[]>(`${environment.apiBaseUrl}/rooms/available?${params}`).subscribe(data => {
      this.occupiedRooms = [...data];
    });
  }

  public getGuestsByRoom() {
    const params = new HttpParams()
      .set('room_number', this.occupiedRoomsForm.value.roomNumber.roomNumber);
    this.http.get<Guest[]>(`${environment.apiBaseUrl}/guests/findByRoom?${params}`).subscribe(data => {
      this.guests = [...data];
    });
  }

  public getRoomByRoomNumber() {
    const params = new HttpParams()
      .set('id', this.roomsForm.value.roomNumber.roomNumber)
    this.http.get<Room[]>(`${environment.apiBaseUrl}/rooms/findById?${params}`).subscribe(data => {
      this.foundRooms = [...data];
    })
  }

  public getNumberOfOccupiedRooms() {
    this.http.get<number>(`${environment.apiBaseUrl}/rooms/numberOfOccupiedRooms`).subscribe(data => {
      this.nrOccupiedRooms = data;
    })
  }
}
