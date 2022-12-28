import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Project } from 'src/app/models/Project';
import { ProjectService } from 'src/app/service/project.service';

@Component({
  selector: 'app-my-projects',
  templateUrl: './my-projects.component.html',
  styleUrls: ['./my-projects.component.css']
})
export class MyProjectsComponent implements OnInit {

  public projects: Array<Project> = [];
  public selectedProject: Project | undefined;
  public ok = true;
  public quantity = 0;
  public username = "";


  ngOnInit(): void {
    this.username = localStorage.getItem('username')!;
    this.getMyProjects();
  }

  constructor(private projectService: ProjectService, private router: Router, private activatedRoute:ActivatedRoute) {
  }

  onSelect(project: Project): void {
    this.selectedProject = project;
  }

  getMyProjects(): void {
    this.projectService.getProjectsForDeveloper(this.username)
      .subscribe(response => {
        this.projects = response.projects;
        console.log(this.projects);
      });
  }

}
