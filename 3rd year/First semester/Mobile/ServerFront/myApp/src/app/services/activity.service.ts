import { Injectable } from '@angular/core';

export interface Activity {
  name: string;
  date: Date;
  info: string;
  startTime: number;
  endTime: number;
}

@Injectable({
  providedIn: 'root'
})
export class ActivityService {
  public activities: Activity[] = [
    {
      name: "Activity 1",
      date: new Date("11/13/2022"),
      info: "info",
      startTime: 1.00,
      endTime: 2.00
    },
    {
      name: "Activity 2",
      date: new Date("11/13/2022"),
      info: "info",
      startTime: 1.00,
      endTime: 2.00
    },
    {
      name: "Activity 3",
      date: new Date("11/13/2022"),
      info: "info",
      startTime: 1.00,
      endTime: 2.00
    },
    {
      name: "Activity 4",
      date: new Date("11/13/2022"),
      info: "info",
      startTime: 1.00,
      endTime: 2.00
    },
    {
      name: "Activity 5",
      date: new Date("11/13/2022"),
      info: "info",
      startTime: 1.00,
      endTime: 2.00
    },
    {
      name: "Activity 6",
      date: new Date("11/13/2022"),
      info: "info",
      startTime: 1.00,
      endTime: 2.00
    },
    {
      name: "Activity 7",
      date: new Date("11/13/2022"),
      info: "info",
      startTime: 1.00,
      endTime: 2.00
    },
    {
      name: "Activity 8",
      date: new Date("11/13/2022"),
      info: "info",
      startTime: 1.00,
      endTime: 2.00
    }
  ];

  constructor() { }

  public getActivities(): Activity[] {
    return this.activities;
  }

  public getActivityById(id: number): Activity {
    return this.activities[id];
  }

  public addActivity(activity: Activity): void {
    this.activities.push(activity);
  }

  public updateActivity(id: number, activity: Activity): void {
    this.activities[id] = activity;
  }

  public deleteActivity(id: number): void {
    this.activities.splice(id, 1);
  }
}
