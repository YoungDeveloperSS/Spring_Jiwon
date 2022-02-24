package young.board.domain.article;

import org.springframework.data.jpa.repository.JpaRepository;
import young.board.domain.article.repository.ArticleRecommendationRepositoryCustom;
import young.board.domain.article.repository.ArticleRepositoryCustom;

public interface ArticleRecommendationRepository extends JpaRepository<ArticleRecommendation, Long>, ArticleRecommendationRepositoryCustom {
}
