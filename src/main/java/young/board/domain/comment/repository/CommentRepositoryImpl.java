package young.board.domain.comment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import young.board.domain.comment.Comment;
import young.board.domain.comment.QComment;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static young.board.domain.comment.QComment.comment;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public Comment findByCommentId(Long comment_id) {
        return queryFactory
                .selectFrom(comment)
                .where(comment.id.eq(comment_id))
                .fetchOne();
    }

    @Override
    public List<Comment> findCommentAll(Long article_id) {
        return queryFactory
                .selectFrom(comment)
                .where(comment.article.id.eq(article_id))
                .fetch();
    }

    @Override
    public List<Comment> findAllByIds(List<Long> commentIds) {
        return queryFactory.selectFrom(comment).distinct()
                .where(
                        comment.id.in(commentIds)
                )
                .orderBy(comment.id.desc())
                .fetch();
    }

    @Override
    public List<Comment> findAllByArticleIdScroll(Long article_id, Long lastCommentsId, int size) {
        List<Long> commentIds = queryFactory
                .select(comment.id).distinct()
                .from(comment)
                .where(lessThanId(lastCommentsId))
                .orderBy(comment.id.desc())
                .limit(size)
                .fetch();

        return findAllByIds(commentIds);
    }

    @Override
    public int findCountsByArticleId(Long article_id) {
        return queryFactory
                .select(comment.id)
                .from(comment)
                .where(comment.article.id.eq(article_id))
                .fetch()
                .size();
    }

    @Override
    public void deleteComment(Long comment_id) {
        queryFactory
                .delete(comment)
                .where(comment.id.eq(comment_id))
                .execute();
    }

    private BooleanExpression lessThanId(Long lastStoreId) {
        if (lastStoreId == null) {
            return null;
        }
        return comment.id.lt(lastStoreId);
    }
}
