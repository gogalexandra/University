package dto.Articles;

import entities.Articles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetArticlesDTO {
    List<Articles> articles;
}
