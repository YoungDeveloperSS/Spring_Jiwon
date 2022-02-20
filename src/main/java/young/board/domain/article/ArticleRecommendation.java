package young.board.domain.article;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import young.board.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ArticleRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_recommendation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "article_id", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    private boolean isUsing;

    @Builder
    private ArticleRecommendation(Long id, Article article, User user, boolean isUsing) {
        this.id = id;
        this.article = article;
        this.user = user;
        this.isUsing = isUsing;
    }

    public static ArticleRecommendation of(Article article, User user){
        return ArticleRecommendation.builder()
                .article(article)
                .user(user)
                .isUsing(true)
                .build();

    }

    public void setRecommendation(){
        this.isUsing = true;
    }

    public void unsetRecommendation(){
        this.isUsing = false;
    }
}
