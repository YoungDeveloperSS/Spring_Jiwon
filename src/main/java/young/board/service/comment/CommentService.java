package young.board.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import young.board.domain.article.Article;
import young.board.domain.article.ArticleRepository;
import young.board.domain.comment.Comment;
import young.board.domain.comment.CommentRepository;
import young.board.domain.user.User;
import young.board.domain.user.UserRepository;
import young.board.service.comment.dto.request.CommentEditRequest;
import young.board.service.comment.dto.request.CommentRequest;
import young.board.service.comment.dto.response.CommentResponse;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CommentResponse> searchAllCommentsByArticleId(Long article_id){
        List<Comment> comments = commentRepository.findCommentAll(article_id);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponses.add(CommentResponse.of(comment));
        }
        return  commentResponses;
    }

    @Transactional
    public Long postComment(CommentRequest commentRequest){
        Article article = articleRepository.findByArticleId(commentRequest.getArticle_id());
        if(article==null){
            throw new IllegalStateException("존재하지 않는 글입니다.");
        }
        User user = userRepository.findByUserId(commentRequest.getUser_id());
        if(user==null){
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        }
        Comment comment = Comment.of(article,user,commentRequest.getValue());
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public Long editComment(CommentEditRequest commentEditrequest){
        Comment comment = commentRepository.findByCommentId(commentEditrequest.getComment_id());
        if(comment==null){
            throw new IllegalStateException("존재하지 않는 댓글입니다.");
        }
        comment.setComment(commentEditrequest.getValue());
        return comment.getId();
    }

    @Transactional
    public void deleteComment(Long comment_id){
        Comment comment = commentRepository.findByCommentId(comment_id);
        if(comment==null){
            throw new IllegalStateException("존재하지 않는 댓글입니다.");
        }
        commentRepository.deleteComment(comment_id);
    }
}
