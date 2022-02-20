package young.board.domain.article.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import young.board.domain.article.Article;
import young.board.domain.article.ArticleRecommendation;
import young.board.domain.article.QArticleRecommendation;

import javax.persistence.EntityManager;
import java.util.List;

import static young.board.domain.article.QArticleRecommendation.*;

@RequiredArgsConstructor
public class ArticleRecommendationRepositoryImpl implements ArticleRecommendationRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public ArticleRecommendation findByArticleIdAndUserId(Long article_id, Long user_id) {
        return queryFactory
                .selectFrom(articleRecommendation)
                .where(articleRecommendation.id.eq(article_id),
                        articleRecommendation.user.id.eq(user_id))
                .fetchOne();
    }
}
