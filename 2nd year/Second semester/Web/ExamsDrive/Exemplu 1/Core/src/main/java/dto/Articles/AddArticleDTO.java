package dto.Articles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleDTO {
    String username;
    Integer journalid;
    String summary;
}

