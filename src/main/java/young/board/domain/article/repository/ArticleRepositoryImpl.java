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
        return em.createQuery("select a from Article a where a.isNotUsing=false and a.id=:article_id", Article.class)
                .setParameter("article_id",article_id).getSingleResult(); //TODO : 조회 시 결과가 없을 때 처리 필요
    }

    @Override
    public List<Article> findArticleAll() {
        return em.createQuery("select a from Article a where a.isNotUsing=false", Article.class)
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
