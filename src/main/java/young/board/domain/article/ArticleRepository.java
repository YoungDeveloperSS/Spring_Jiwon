package young.board.domain.article;

import org.springframework.data.jpa.repository.JpaRepository;
import young.board.domain.article.repository.ArticleRepositoryCustom;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
}
