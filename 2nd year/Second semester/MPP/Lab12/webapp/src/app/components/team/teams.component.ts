import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Team} from "../../models/team-model";
import {TeamsService} from "../../service/teams-service/team-service";
import {addTeam} from "../../dto/teams/addTeam";

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent implements OnInit {
  teams: Array<Team>;
  selectedTeam: Team;

  addTeamForm = new FormGroup({
    name: new FormControl('', Validators.required),
  })

  addTeamFormMethod(data) {
    let id = data.teamId.trim();
    let name = data.name.trim();
    if (!name || !id) {
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

  constructor(private teamsService: TeamsService, private router: Router) {
  }

  onSelect(team: Team): void {
    this.selectedTeam = team;
  }


  add(name: string): void {
    name = name.trim();
    if (!name ) {
      return;
    }
    this.teamsService.add({name} as addTeam)
      .subscribe(_ => this.getTeams());

  }

  getTeams(): void {
    this.teamsService.getTeams()
      .subscribe(response => {
        console.log(response);
        this.teams = response['teams']
      });
    console.log(this.teams);
  }

  delete(team: Team): void {
    this.teams = this.teams.filter(c => c !== team);
    this.teamsService.delete(team.id).subscribe();
  }

  save(): void {
    this.teamsService.update(this.selectedTeam).subscribe();
  }

  ngOnInit(): void {
    this.getTeams();
  }

}
