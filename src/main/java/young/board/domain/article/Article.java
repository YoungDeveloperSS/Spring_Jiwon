package young.board.domain.article;

import lombok.AccessLevel;
import lombok.Builder;
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

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private ArticleCategory category;

    private boolean isNotUsing;

    @Builder
    private Article(Long id, String title, String content, String nickname, String date, String imageUrl, ArticleCategory category, boolean isNotUsing) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.date = date;
        this.imageUrl = imageUrl;
        this.category = category;
        this.isNotUsing = isNotUsing;
    }

    public static Article of(String title, String content, String nickname, String date, String imageUrl){
        return Article.builder()
                .title(title)
                .content(content)
                .nickname(nickname)
                .date(date)
                .imageUrl(imageUrl)
                .build();
    }

    public void setArticle(String title, String content, String nickname, String date){
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.date = date;
    }

    public void unSetIsUsing(){
        this.isNotUsing = true;
    }
}
