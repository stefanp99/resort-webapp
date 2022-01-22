import { Component, OnInit } from '@angular/core';
import { Activity } from './activity';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { FormBuilder } from '@angular/forms';
import { Staff } from '../staff/staff';

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit {
  upcomingActivitiesMap;
  upcomingActivities: Activity[];
  weekdays: string[];
  activities: Activity[];
  staff: Staff[];

  upcomingActivitiesForm = this.formBuilder.group({
    dayOfWeek: ''
  });

  staffByActivityForm = this.formBuilder.group({
    activity: ''
  });


  constructor(private http: HttpClient, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.weekdays = [];
    this.weekdays.push('Sunday');
    this.weekdays.push('Monday');
    this.weekdays.push('Tuesday');
    this.weekdays.push('Wednesday');
    this.weekdays.push('Thursday');
    this.weekdays.push('Friday');
    this.weekdays.push('Saturday');
    this.getAllActivities();
  }

  getAllActivities() {
    this.http.get<Activity[]>(`${environment.apiBaseUrl}/activities/all`).subscribe(data => {
      this.activities = [...data];
    })
  }

  getUpcomingActivities() {
    this.upcomingActivitiesMap = new Map<number, Activity[]>();
    let params = new HttpParams();
    for (let index = 0; index < 7; index++) {
      params.set('daysDifference', String(index));
      this.http.get<Activity[]>(`${environment.apiBaseUrl}/activities/upcoming?${params}`).subscribe(data => {
        this.upcomingActivitiesMap.set(index, [...data]);
      })
    }
  }

  getActivitiesAtIndex() {
    let currentDate = new Date;
    let dayOfWeekIndex = this.upcomingActivitiesForm.value.dayOfWeek;
    if (this.weekdays.indexOf(dayOfWeekIndex) > currentDate.getDay())
      var params = new HttpParams()
        .set('daysDifference', String(this.weekdays.indexOf(dayOfWeekIndex) - currentDate.getDay()));
    else
      var params = new HttpParams()
        .set('daysDifference', String(currentDate.getDay() - this.weekdays.indexOf(dayOfWeekIndex)));
    this.http.get<Activity[]>(`${environment.apiBaseUrl}/activities/upcoming?${params}`).subscribe(data => {
      this.upcomingActivities = [...data];
    })
  }

  getStaffByActivity() {
    const params = new HttpParams()
      .set('activity', this.staffByActivityForm.value.activity.activityName);
    this.http.get<Staff[]>(`${environment.apiBaseUrl}/activities/staff/byActivity?${params}`).subscribe(data => {
      this.staff = [...data];
    })
  }

}
