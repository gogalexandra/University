package service;

import dto.Project.AddProjectDTO;
import dto.Project.GetProjectsDTO;
import entities.Project;
import org.springframework.stereotype.Service;
import repository.ProjectRepository;

@Service
public class ProjectService {
    public ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public GetProjectsDTO getProjects(){
        return new GetProjectsDTO(this.projectRepository.findAll());
    }

    public GetProjectsDTO getProjectsOfDeveloper(String name){
        return new GetProjectsDTO(this.projectRepository.findAllByMembersContaining(name));
    }

    public Project getProjectByName(String name){
        return this.projectRepository.findProjectByName(name);
    }

    public void addProject(AddProjectDTO dto){
        this.projectRepository.save(new Project(dto));
    }


}
