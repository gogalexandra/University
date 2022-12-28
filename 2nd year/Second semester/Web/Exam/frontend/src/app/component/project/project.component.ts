import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Project } from 'src/app/models/Project';
import { ProjectService } from 'src/app/service/project.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  public projects: Array<Project> = [];
  public selectedProject: Project | undefined;
  public ok = true;
  public quantity = 0;
  public username = "";


  ngOnInit(): void {
    this.getProjects();
    this.username = localStorage.getItem('username')!;
  }

  constructor(private projectService: ProjectService, private router: Router, private activatedRoute:ActivatedRoute) {
  }

  onSelect(project: Project): void {
    this.selectedProject = project;
  }

  getProjects(): void {
    this.projectService.getProjects()
      .subscribe(response => {
        this.projects = response.projects;
        console.log(this.projects);
      });
  }

}
