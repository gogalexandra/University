package service;

import dto.Articles.AddArticleDTO;
import dto.Articles.GetArticlesDTO;
import entities.Articles;
import org.springframework.stereotype.Service;
import repository.ArticlesRepository;
import repository.JournalsRepository;

@Service
public class ArticlesService {
    private ArticlesRepository articlesRepository;

    public ArticlesService(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    public void addArticle(AddArticleDTO dto){
        this.articlesRepository.save(new Articles(dto));
    }
}
