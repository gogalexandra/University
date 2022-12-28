import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { addDriver } from 'src/app/dtos/driver/addDriver';
import { updateDriver } from 'src/app/dtos/driver/updateDriver';
import { Driver } from 'src/app/models/driver';
import { DriverServiceService } from 'src/app/services/driver-service.service';

@Component({
  selector: 'app-driver',
  templateUrl: './driver.component.html',
  styleUrls: ['./driver.component.scss']
})
export class DriverComponent implements OnInit {

  public drivers: Array<Driver> = [];
  public selectedDriver: Driver | undefined;
  public ok = true;


  ngOnInit(): void {
    this.getDrivers();
    let keys = Object.keys(this.drivers);
  }

  constructor(private driversService: DriverServiceService, private router: Router, private cd: ChangeDetectorRef) {
    this.getDrivers();
  }


  addDriverForm = new FormGroup({
    driverName: new FormControl('', Validators.required),
    driverExperience: new FormControl('', Validators.required),
    driverTeam: new FormControl('', Validators.required),
  })

  addDriverFormMethod(data: { driverName: string; experience: string; team: string }) {
    let name = data.driverName.trim();
    let experience = Number(data.experience.trim());
    let team = Number(data.team.trim());
    if (!name || !experience || !team) {
      alert("Please fill all fields");
      return;
    }
    this.driversService.add({
      name: name,
      experience: experience,
      team: team,
    } as addDriver)
      .subscribe(
        _ => this.getDrivers()
      )
  }

  onSubmit() {
    this.addDriverFormMethod(this.addDriverForm.value)
  }

  onSelect(driver: Driver): void {
    this.selectedDriver = driver;
    this.cd.detectChanges();
  }


  add(name: string, experienceStr: string, teamStr: string): void {
    name = name.trim();
    let experience = Number(experienceStr);
    let team = Number(teamStr);
    if (!name || !experience || !team ) {
      return;
    }
    this.driversService.add({name, experience, team} as addDriver)
      .subscribe(_ => this.getDrivers());

  }

  getDrivers(): void {
    this.driversService.getDrivers()
      .subscribe(response => {
        this.drivers = response.drivers;
        console.log(this.drivers);
      });
  }

  delete(driver: Driver): void {
    this.drivers = this.drivers.filter(c => c !== driver);
    this.driversService.delete(driver.id).subscribe();
  }

  save(name: string, experienceStr: number, teamStr: number): void {
    let id = this.selectedDriver?.id;
    name = name.trim();
    let experience = Number(experienceStr);
    let team = Number(teamStr);
    if(this.selectedDriver != undefined){
      this.driversService.update({id, name, experience, team} as updateDriver).subscribe();
    }
  }

  public redirectToAdd(): void{
    
  }
}
