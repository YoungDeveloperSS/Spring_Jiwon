package young.board.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import young.board.domain.comment.repository.CommentRepositoryCustom;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
