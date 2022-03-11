package young.board.domain.article.repository;

import young.board.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryCustom {
    Article findByArticleId(Long article_id); //Optional<Article>
    List<Article> findArticleAll();
    void insertArticle(Article article);
}
