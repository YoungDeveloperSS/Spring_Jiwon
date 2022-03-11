package young.board.domain.comment;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import young.board.domain.article.Article;
import young.board.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "article_id", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    private String value;

    @Builder
    private Comment(Long id, Article article, User user, String value) {
        this.id = id;
        this.article = article;
        this.user = user;
        this.value = value;
    }

    public static Comment of(Article article, User user, String value){
        return Comment.builder()
                .article(article)
                .user(user)
                .value(value)
                .build();

    }

    public void setComment(String value){
        this.value = value;
    }
}
