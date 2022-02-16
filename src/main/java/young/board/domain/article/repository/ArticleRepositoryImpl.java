package young.board.domain.article.repository;

import lombok.RequiredArgsConstructor;
import young.board.domain.article.Article;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom{
    private final EntityManager em;

    @Override
    public Article findByArticleId(Long article_id) {
        return em.find(Article.class,article_id);
    }

    @Override
    public List<Article> findArticleAll() {
        return em.createQuery("select a from Article a", Article.class)
                .getResultList();
    }

    @Override
    public void insertArticle(Article article) {
        if(article.getId() == null){
            em.persist(article);
        }
        else{
            em.merge(article);
        }
    }
}
