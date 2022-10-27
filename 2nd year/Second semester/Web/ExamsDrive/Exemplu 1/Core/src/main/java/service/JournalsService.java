package service;

import dto.Articles.GetArticlesDTO;
import dto.Journals.AddJournalDTO;
import dto.Journals.GetJournalsDTO;
import entities.Journals;
import org.springframework.stereotype.Service;
import repository.ArticlesRepository;
import repository.JournalsRepository;

@Service
public class JournalsService {
    private JournalsRepository journalsRepository;
    private ArticlesRepository articlesRepository;

    public JournalsService(JournalsRepository journalsRepository, ArticlesRepository articlesRepository) {
        this.journalsRepository = journalsRepository;
        this.articlesRepository = articlesRepository;
    }

    public GetJournalsDTO getJournalByName(String name){
        return new GetJournalsDTO(this.journalsRepository.findJournalsByName(name));
    }

    public void addJournal(AddJournalDTO dto){
        this.journalsRepository.save(new Journals(dto));
    }

    public GetArticlesDTO getArticlesOfJournals(String username, String name){
        Integer journalid = this.journalsRepository.findJournalsByName(name).getId();
        return new GetArticlesDTO(this.articlesRepository.findAllByUsernameAndAndJournalid(username, journalid));
    }
}
