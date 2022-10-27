import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Driver} from "../../models/driver-model";
import {DriversService} from "../../service/drivers-service/driver-service";
import {addDriver} from "../../dto/drivers/addDriver";
import {Race} from "../../models/race-model";
import {RacesService} from "../../service/races-service/races-service";
import {addRace} from "../../dto/races/addRace";

@Component({
  selector: 'app-races',
  templateUrl: './races.component.html',
  styleUrls: ['./races.component.css']
})
export class RacesComponent implements OnInit {
  races: Array<Race>;
  selectedRace: Race;

  addRaceForm = new FormGroup({
    name: new FormControl('', Validators.required),
  })

  addRaceFormMethod(data) {
    let id = data.raceId.trim();
    let name = data.name.trim();
    if (!name || !id) {
      alert("Please fill all fields");
      return;
    }
    this.racesService.add({
      name: name,
    } as addRace)
      .subscribe(
        _ => this.getRaces()
      )
  }

  onSubmit() {
    this.addRaceFormMethod(this.addRaceForm.value)
  }

  constructor(private racesService: RacesService, private router: Router) {
  }

  onSelect(race: Race): void {
    this.selectedRace = race;
  }


  add(name: string): void {
    name = name.trim();
    if (!name ) {
      return;
    }
    this.racesService.add({name} as addRace)
      .subscribe(_ => this.getRaces());

  }

  getRaces(): void {
    this.racesService.getRaces()
      .subscribe(races => {
        console.log(races);
        this.races = races.races
      });
    console.log(this.races);
  }

  delete(race: Race): void {
    this.races = this.races.filter(c => c !== race);
    this.racesService.delete(race.id).subscribe();
  }

  save(): void {
    this.racesService.update(this.selectedRace).subscribe();
  }

  // orderCatsByAge(): void {
  //   this.drivers.sort((a: Cat, b: Cat) => a.catYears - b.catYears)
  // }

  ngOnInit(): void {
    this.getRaces();
  }

}
