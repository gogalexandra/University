import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { addParticipation } from 'src/app/dtos/participation/addParticipation';
import { Participation } from 'src/app/models/participation';
import { ParticipationServiceService } from 'src/app/services/participation-service.service';

@Component({
  selector: 'app-participation',
  templateUrl: './participation.component.html',
  styleUrls: ['./participation.component.scss']
})
export class ParticipationComponent implements OnInit {

  participations: Array<Participation> = [];
  selectedParticipation: Participation | undefined;

  addParticipationForm = new FormGroup({
    team: new FormControl('', Validators.required),
    race: new FormControl('', Validators.required),
  })

  addparticipationFormMethod(data: { teamId: string; raceId: string; points: string; }) {
    let teamId = data.teamId.trim();
    let raceId = data.raceId.trim();
    if (!teamId || !raceId) {
      alert("Please fill all fields");
      return;
    }
    this.participationsService.add({
      teamId: Number(teamId),
      raceId: Number(raceId),
    } as addParticipation)
      .subscribe(
        (        _: any) => this.getParticipations()
      )
  }

  onSubmit() {
    this.addparticipationFormMethod(this.addParticipationForm.value)
  }

  constructor(private participationsService: ParticipationServiceService, private router: Router) {
    this.getParticipations();
  }

  onSelect(participation: Participation): void {
    this.selectedParticipation = participation;
  }


  add(teamId: number, raceId: number): void {

    this.participationsService.add({teamId, raceId} as addParticipation)
      .subscribe((_: any) => this.getParticipations());

  }

  getParticipations(): void {
    this.participationsService.getParticipations()
      .subscribe(response => {
        console.log(response);
        this.participations = response.participations
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


  ngOnInit(): void {
    this.getParticipations();
  }
}
