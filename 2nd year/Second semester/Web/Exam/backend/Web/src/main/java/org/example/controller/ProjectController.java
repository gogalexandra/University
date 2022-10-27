package org.example.controller;

import dto.Project.AddProjectDTO;
import dto.Project.GetProjectsDTO;
import entities.Project;
import org.springframework.web.bind.annotation.*;
import service.ProjectService;

@RestController
public class ProjectController {
    public ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    GetProjectsDTO getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/projects/{name}")
    GetProjectsDTO getProjectsofDeveloper(@PathVariable String name) {
        return projectService.getProjectsOfDeveloper(name);
    }

    @GetMapping("/projects/get/{name}")
    Project getProjectByName(@PathVariable String name) {
        return projectService.getProjectByName(name);
    }

    @PostMapping("/projects")
    void add(@RequestBody AddProjectDTO dto) {
        projectService.addProject(dto);
    }


}
