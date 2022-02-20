package young.board.domain.comment.repository;

import young.board.domain.comment.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    Comment findByCommentId(Long comment_id);
    List<Comment> findCommentAll(Long article_id);
    List<Comment> findAllByIds(List<Long> commentIds);
    List<Comment> findAllByArticleIdScroll(Long article_id,Long lastCommentsId,int size);
    int findCountsByArticleId(Long article_id);
    void deleteComment(Long comment_id);
}
