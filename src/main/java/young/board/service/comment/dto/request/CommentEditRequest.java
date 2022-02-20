package young.board.service.comment.dto.request;

import lombok.Getter;

@Getter
public class CommentEditRequest {
    private Long comment_id;

    private String value;
}
