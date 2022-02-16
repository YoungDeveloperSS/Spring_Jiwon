package young.board.service.article.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleRequest {
    private String title;

    private String content;

    private String nickname;

    private String date;
}
