import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { addDriver } from 'src/app/dtos/driver/addDriver';
import { AddDriverToTeam } from 'src/app/dtos/team/addDriverToTeam';
import { addTeam } from 'src/app/dtos/team/addTeam';
import { updateTeam } from 'src/app/dtos/team/updateTeam';
import { Driver } from 'src/app/models/driver';
import { Team } from 'src/app/models/team';
import { DriverServiceService } from 'src/app/services/driver-service.service';
import { TeamServiceService } from 'src/app/services/team-service.service';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.scss']
})
export class TeamComponent implements OnInit {

  public teams: Array<Team> = [];
  public selectedTeam: Team | undefined;
  public name = "";
  public driverId = 0;
  public driversOfTeam: Array<Driver> = [];


  public addTeamForm = new FormGroup({
    teamName: new FormControl('', Validators.required),
  })

  public addTeamFormMethod(data: {name: string; }) {
    let name = data.name.trim();
    if (!name) {
      alert("Please fill all fields");
      return;
    }
    this.teamsService.add({
      name: name,
    } as addTeam)
      .subscribe(
        _ => this.getTeams()
      )
  }

  onSubmit() {
    this.addTeamFormMethod(this.addTeamForm.value)
  }

  constructor(private teamsService: TeamServiceService, private driversService: DriverServiceService, private router: Router, public dialog: MatDialog) {
    this.getTeams();
  }

  onSelect(team: Team): void {
    this.selectedTeam = team;
    this.driversService.getDriversOfTeam(team.id).subscribe(response => {
      this.driversOfTeam = response.drivers;
      console.log()
    });
  }


  public add(name: string): void {
    name = name.trim();
    if (!name ) {
      return;
    }
    this.teamsService.add({name} as addTeam)
      .subscribe(_ => this.getTeams());

  }

  public getTeams(): void {
    this.teamsService.getTeams()
      .subscribe(response => {
        console.log(response);
        this.teams = response.teams
        console.log(this.teams);
      });
  }

  public delete(team: Team): void {
    if (this.teams != undefined){
      this.teams = this.teams.filter(c => c !== team);
      this.teamsService.delete(team.id).subscribe();
    }

  }

  public save(name: String): void {
    let id = this.selectedTeam?.id;
    if (this.selectedTeam != undefined){
      this.teamsService.update({id, name} as updateTeam).subscribe();
    }
  }

  ngOnInit(): void {
    this.getTeams();
  }



  onDeleteClick(driverId: number): void {
    if (this.selectedTeam !== undefined){    
      this.teamsService.deleteDriverFromTeam(this.selectedTeam.id, driverId);} 
  }

  addDriverToTeam(): void{
    let driverId = Number(this.driverId);
    if (this.selectedTeam !== undefined){    
      this.teamsService.addDriverToTeam(this.selectedTeam.id, driverId);}
  }

}
