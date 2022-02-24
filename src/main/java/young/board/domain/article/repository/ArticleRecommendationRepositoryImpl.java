package young.board.domain.article.repository;

import lombok.RequiredArgsConstructor;
import young.board.domain.article.Article;
import young.board.domain.article.ArticleRecommendation;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class ArticleRecommendationRepositoryImpl implements ArticleRecommendationRepositoryCustom{
    private final EntityManager em;

    @Override
    public ArticleRecommendation findByArticleIdAndUserId(Long article_id, Long user_id) {
        return em.createQuery("select a from ArticleRecommendation a where a.article.id=:article_id and a.user.id=:user_id", ArticleRecommendation.class)
                .setParameter("article_id",article_id)
                .setParameter("user_id",user_id)
                .getSingleResult(); //TODO : 조회 시 결과가 없을 때 처리 필요
    }
}
