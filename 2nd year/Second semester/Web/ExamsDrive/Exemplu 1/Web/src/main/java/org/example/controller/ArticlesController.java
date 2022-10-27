package org.example.controller;

import dto.Articles.AddArticleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ArticlesService;

@RestController
public class ArticlesController {
    public ArticlesService articlesService;

    public ArticlesController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @PostMapping("/articles")
    void add(@RequestBody AddArticleDTO dto) {
        this.articlesService.addArticle(dto);
    }
}
