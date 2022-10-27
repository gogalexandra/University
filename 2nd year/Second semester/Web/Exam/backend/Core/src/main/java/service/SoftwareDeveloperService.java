package service;

import dto.Project.AddProjectDTO;
import dto.Project.GetProjectsDTO;
import entities.Project;
import entities.SoftwareDeveloper;
import org.springframework.stereotype.Service;
import repository.ProjectRepository;
import repository.SoftwareDeveloperRepository;

@Service
public class SoftwareDeveloperService {
    public SoftwareDeveloperRepository softwareDeveloperRepository;

    public SoftwareDeveloperService(SoftwareDeveloperRepository softwareDeveloperRepository) {
        this.softwareDeveloperRepository = softwareDeveloperRepository;
    }

    public SoftwareDeveloper getSoftwareDeveloper(String name){
        return this.softwareDeveloperRepository.findSoftwareDeveloperByName(name);
    }


}
