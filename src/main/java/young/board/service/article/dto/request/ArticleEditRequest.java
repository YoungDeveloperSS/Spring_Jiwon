package young.board.service.article.dto.request;

import lombok.Getter;

@Getter
public class ArticleEditRequest {
    private Long id;

    private String title;

    private String content;

    private String nickname;

    private String date;
}
