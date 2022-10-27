import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Driver} from "../../models/driver-model";
import {DriversService} from "../../service/drivers-service/driver-service";
import {addDriver} from "../../dto/drivers/addDriver";

@Component({
  selector: 'app-drivers',
  templateUrl: './drivers.component.html',
  styleUrls: ['./drivers.component.css']
})
export class DriversComponent implements OnInit {
  public drivers: Array<Driver>;
  public selectedDriver: Driver;
  public ok = true;


  ngOnInit(): void {
    this.getDrivers();
    let keys = Object.keys(this.drivers);
  }

  constructor(private driversService: DriversService, private router: Router, private cd: ChangeDetectorRef) {
    this.getDrivers();

  }


  addDriverForm = new FormGroup({
    name: new FormControl('', Validators.required),
  })
  addDriverFormMethod(data) {
    //let id = data.driverId.trim();
    let name = data.driverName.trim();
    if (!name) {
      alert("Please fill all fields");
      return;
    }
    this.driversService.add({
      //id: Number(id),
      name: name,
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


  add(name: string): void {
    name = name.trim();
    if (!name ) {
      return;
    }
    this.driversService.add({name} as addDriver)
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

  save(): void {
    this.driversService.update(this.selectedDriver).subscribe();
  }

}
