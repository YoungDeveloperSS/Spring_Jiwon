package young.board.service.comment.dto.response;

import lombok.*;
import young.board.domain.article.Article;
import young.board.domain.comment.Comment;
import young.board.domain.user.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {

    private Long id;

    private String value;

    @Builder
    public CommentResponse(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public static CommentResponse of(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .value(comment.getValue())
                .build();
    }
}
