package org.example.controller;

import dto.Articles.GetArticlesDTO;
import dto.Journals.AddJournalDTO;
import dto.Journals.GetJournalsDTO;
import org.springframework.web.bind.annotation.*;
import service.JournalsService;

@RestController
public class JournalsController {
    public JournalsService journalsService;

    public JournalsController(JournalsService journalsService) {
        this.journalsService = journalsService;
    }

    @GetMapping("/journal/{name}")
    GetJournalsDTO getJournalsByName(@PathVariable String name) {
        return journalsService.getJournalByName(name);
    }

    @PostMapping("/journal")
    void add(@RequestBody AddJournalDTO dto) {
        journalsService.addJournal(dto);
    }

    @GetMapping("/journal/{username}/{name}")
    GetArticlesDTO getJournalsByName(@PathVariable String username, @PathVariable String name) {
        return journalsService.getArticlesOfJournals(username, name);
    }

}
