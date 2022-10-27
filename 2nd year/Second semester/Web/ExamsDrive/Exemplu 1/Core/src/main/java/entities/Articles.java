package entities;

import dto.Articles.AddArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Articles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String username;
    private Integer journalid;
    private String summary;
    private Date date;

    @PrePersist
    void date() {
        this.date = new Date();
    }

    public Articles(AddArticleDTO dto){
        username = dto.getUsername();
        journalid = dto.getJournalid();
        summary = dto.getSummary();
    }

    @Override
    public String toString() {
        return "Articles{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", journalid=" + journalid +
                ", summary='" + summary + '\'' +
                ", date=" + date +
                '}';
    }
}