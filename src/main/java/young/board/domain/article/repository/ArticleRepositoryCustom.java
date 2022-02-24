package young.board.domain.article.repository;

import young.board.domain.article.Article;

import java.util.List;

public interface ArticleRepositoryCustom {
    Article findByArticleId(Long article_id);
    List<Article> findArticleAll();
    void insertArticle(Article article);
}
