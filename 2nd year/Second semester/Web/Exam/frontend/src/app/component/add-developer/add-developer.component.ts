import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AddProjectDTO } from 'src/app/dto/AddProjectDTO';
import { ProjectService } from 'src/app/service/project.service';
import { SoftwareDeveloperService } from 'src/app/service/software-developer.service';

@Component({
  selector: 'app-add-developer',
  templateUrl: './add-developer.component.html',
  styleUrls: ['./add-developer.component.css']
})
export class AddDeveloperComponent implements OnInit {

  public developername: string = "";
  public projectname: string = "";

  addForm = new FormGroup({
    developername: new FormControl('', Validators.required),
    projectname: new FormControl('', Validators.required),
  })

  constructor( private projectService: ProjectService, private developerService: SoftwareDeveloperService) { }

  ngOnInit(): void {
  }

  public addDeveloper(projectname: string, developername: string): void{
    let dev: any
    this.developerService.getSoftwareDeveloperByName(developername).subscribe(response => {
      dev = response;
    });;
    if (dev == null)
      return;
    else{
      let project: any;
      this.projectService.getProjectsByName(projectname).subscribe(response => {
        project = response;
      });
      if (project == null){
        this.projectService.add({
          ProjectManagerID: 0,
          name: projectname,
          description: "",
          members: developername,
        } as AddProjectDTO)
      }
    }
  }

}
