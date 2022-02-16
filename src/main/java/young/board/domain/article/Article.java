package young.board.domain.article;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import young.board.domain.article.ArticleCategory;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;

    private String content;

    private String nickname;

    private String date;

    @Enumerated(EnumType.STRING)
    private ArticleCategory category;

    private boolean isUsing;

    public static Article createArticle(String title, String content, String nickname, String date){
        Article article = new Article();
        article.title = title;
        article.content = content;
        article.nickname = nickname;
        article.date = date;
        return article;
    }
}
