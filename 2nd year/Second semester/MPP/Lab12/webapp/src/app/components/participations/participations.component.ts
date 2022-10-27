import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Participation} from "../../models/participation-model";
import {ParticipationsService} from "../../service/participations-service/participations-service";
import {addParticipation} from "../../dto/participations/addParticipation";

@Component({
  selector: 'app-participations',
  templateUrl: './participations.component.html',
  styleUrls: ['./participations.component.css']
})
export class ParticipationsComponent implements OnInit {
  participations: Array<Participation>;
  selectedParticipation: Participation;

  addParticipationForm = new FormGroup({
    teamId: new FormControl('', Validators.required),
    raceId: new FormControl('', Validators.required),
    points: new FormControl('', Validators.required),

  })

  addparticipationFormMethod(data) {
    let teamId = data.teamId.trim();
    let raceId = data.raceId.trim();
    let points = data.points.trim();
    if (!teamId || !raceId || !points) {
      alert("Please fill all fields");
      return;
    }
    this.participationsService.add({
      teamId: Number(teamId),
      raceId: Number(raceId),
      points: Number(points),
    } as addParticipation)
      .subscribe(
        _ => this.getParticipations()
      )
  }

  onSubmit() {
    this.addparticipationFormMethod(this.addParticipationForm.value)
  }

  constructor(private participationsService: ParticipationsService, private router: Router) {
  }

  onSelect(participation: Participation): void {
    this.selectedParticipation = participation;
  }


  add(teamId: number, raceId: number, points: number): void {

    this.participationsService.add({teamId, raceId, points} as addParticipation)
      .subscribe(_ => this.getParticipations());

  }

  getParticipations(): void {
    this.participationsService.getParticipations()
      .subscribe(participations => {
        console.log(participations);
        this.participations = participations.participations
      });
    console.log(this.participations);
  }

  delete(participation: Participation): void {
    this.participations = this.participations.filter(c => c !== participation);
    this.participationsService.delete(participation.id).subscribe();
  }

  // save(): void {
  //   this.participationsService.update(this.selectedParticipation).subscribe();
  // }

  // orderCatsByAge(): void {
  //   this.drivers.sort((a: Cat, b: Cat) => a.catYears - b.catYears)
  // }

  ngOnInit(): void {
    this.getParticipations();
  }

}
