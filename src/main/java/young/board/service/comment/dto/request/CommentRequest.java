package young.board.service.comment.dto.request;

import lombok.Getter;

@Getter
public class CommentRequest {
    private Long article_id;

    private Long user_id;

    private String value;

}
