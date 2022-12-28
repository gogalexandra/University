import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { addRace } from 'src/app/dtos/race/addRace';
import { updateRace } from 'src/app/dtos/race/updateRace';
import { Race } from 'src/app/models/race';
import { RaceServiceService } from 'src/app/services/race-service.service';

@Component({
  selector: 'app-race',
  templateUrl: './race.component.html',
  styleUrls: ['./race.component.scss']
})
export class RaceComponent implements OnInit {

  races: Array<Race> = [];
  selectedRace: Race | undefined;

  addRaceForm = new FormGroup({
    location: new FormControl('', Validators.required),
    raceDate: new FormControl('', Validators.required),
  })

  addRaceFormMethod(data: { location: string;  date: string; }) {
    let location = data.location.trim();
    let date = data.date.trim();
    if (!location || !date) {
      alert("Please fill all fields");
      return;
    }
    this.racesService.add({
      location: location,
      date : date
    } as addRace)
      .subscribe(
        (        _: any) => this.getRaces()
      )
  }

  onSubmit() {
    this.addRaceFormMethod(this.addRaceForm.value)
  }

  constructor(private racesService: RaceServiceService, private router: Router) {
  }

  onSelect(race: Race): void {
    this.selectedRace = race;
  }


  add(location: string, date: string): void {
    location = location.trim();
    date = date.trim();
    if (!location || !date ) {
      return;
    }
    this.racesService.add({location: location, date: date} as addRace)
      .subscribe(_ => this.getRaces());

  }

  getRaces(): void {
    this.racesService.getRaces()
      .subscribe(response => {
        console.log(response);
        this.races = response.races
      });
  }

  delete(race: Race): void {
    this.races = this.races.filter(c => c !== race);
    this.racesService.delete(race.id).subscribe();
  }

  save(location: string, date: string): void {
    let id = this.selectedRace?.id;
    if (this.selectedRace != undefined){
      this.racesService.update({id, location, date} as updateRace).subscribe();
    }
  }

  ngOnInit(): void {
    this.getRaces();
  }

}
