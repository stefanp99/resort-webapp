import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { FormBuilder } from '@angular/forms';
import { timer } from 'rxjs';
import { Staff } from './staff';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent implements OnInit {
  staffList: Staff[];

  addForm = this.formBuilder.group({
    name: '',
    surname: '',
    occupation: ''
  });

  updateForm = this.formBuilder.group({
    staffId: 0,
    name: '',
    surname: '',
    occupation: ''
  });

  deleteForm = this.formBuilder.group({
    staffId: 0
  });

  findByHotelForm = this.formBuilder.group({
    hotelNumber: 0
  })

  constructor(private http: HttpClient, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  refresh(): void {
    window.location.reload();
  }

  public addStaff() {
    console.log('Staff added');
    console.log(this.addForm.value);

    const params = new HttpParams()
      .set('name', this.addForm.value.name)
      .set('surname', this.addForm.value.surname)
      .set('occupation', this.addForm.value.occupation);

    console.log(`${environment.apiBaseUrl}/staff/add?${params}`)

    this.http.post<Staff>(`${environment.apiBaseUrl}/staff/add?${params}`, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }).subscribe(response => console.log(response));

    timer(500).subscribe(x => this.refresh());
  }

  public updateStaff() {
    console.log('Staff updated');
    console.log(this.updateForm.value);

    const params = new HttpParams()
      .set('staffId', this.updateForm.value.staffId)
      .set('name', this.updateForm.value.name)
      .set('surname', this.updateForm.value.surname)
      .set('occupation', this.updateForm.value.occupation);

    this.http.put<Staff>(`${environment.apiBaseUrl}/staff/update?${params}`, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }).subscribe(response => console.log(response));

    timer(500).subscribe(x => this.refresh());
  }

  public deleteStaff() {
    console.log('Staff removed');
    console.log(this.deleteForm.value);

    const params = new HttpParams()
      .set('staffId', this.deleteForm.value.staffId);

    this.http.delete<Staff>(`${environment.apiBaseUrl}/staff/delete?${params}`, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }).subscribe(response => console.log(response));

    timer(500).subscribe(x => this.refresh());
  }

  public findByHotel() {
    const params = new HttpParams()
      .set('hotelNumber', this.findByHotelForm.value.hotelNumber);
    this.http.get<Staff[]>(`${environment.apiBaseUrl}/staff/byHotel?${params}`).subscribe(data => {
      this.staffList = [...data];
    })
  }

}
