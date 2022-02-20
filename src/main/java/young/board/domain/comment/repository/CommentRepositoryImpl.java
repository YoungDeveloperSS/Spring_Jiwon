package young.board.domain.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import young.board.domain.comment.Comment;
import young.board.domain.comment.QComment;

import javax.persistence.EntityManager;
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
    public void deleteComment(Long comment_id) {
        queryFactory
                .delete(comment)
                .where(comment.id.eq(comment_id))
                .execute();
    }
}
