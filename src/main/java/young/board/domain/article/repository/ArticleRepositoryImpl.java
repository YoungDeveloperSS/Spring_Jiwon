package young.board.domain.article.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import young.board.domain.article.Article;

import javax.persistence.EntityManager;
import java.util.List;

import static young.board.domain.article.QArticle.*;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public Article findByArticleId(Long article_id) {
        return queryFactory
                .selectFrom(article)
                .where(article.isNotUsing.eq(false),
                        article.id.eq(article_id))
                .fetchOne();
    }

    @Override
    public List<Article> findArticleAll() {
        return queryFactory
                .selectFrom(article)
                .where(article.isNotUsing.eq(false))
                .fetch();
    }

    @Override
    public void insertArticle(Article article) {
        if(article.getId() == null){ //Querydsl은 insert를 가지고 있지 않다.
            em.persist(article);
        }
        else{
            em.merge(article);
        }
    }
}
