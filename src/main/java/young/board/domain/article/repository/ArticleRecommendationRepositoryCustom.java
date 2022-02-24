package young.board.domain.article.repository;

import young.board.domain.article.Article;
import young.board.domain.article.ArticleRecommendation;

import java.util.List;

public interface ArticleRecommendationRepositoryCustom {
    ArticleRecommendation findByArticleIdAndUserId(Long article_id, Long user_id);
}
