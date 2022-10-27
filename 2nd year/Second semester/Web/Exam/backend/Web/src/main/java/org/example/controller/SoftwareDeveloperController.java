package org.example.controller;

import entities.SoftwareDeveloper;
import org.springframework.web.bind.annotation.*;
import service.SoftwareDeveloperService;

@RestController
public class SoftwareDeveloperController {
    public SoftwareDeveloperService softwareDeveloperService;

    public SoftwareDeveloperController(SoftwareDeveloperService softwareDeveloperService) {
        this.softwareDeveloperService = softwareDeveloperService;
    }

    @GetMapping("/developer/{name}")
    SoftwareDeveloper getSoftwareDeveloperByName(@PathVariable String name) {
        return softwareDeveloperService.getSoftwareDeveloper(name);
    }

}
