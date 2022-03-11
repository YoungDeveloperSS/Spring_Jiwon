package young.board.service.article.dto.response;

import lombok.*;
import young.board.domain.article.Article;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleResponse {

    private Long id;//TODO : 필수

    private String title;

    private String content;

    private String nickname;

    private String date;

    @Builder
    public ArticleResponse(String title, String content, String nickname, String date) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.date = date;
    }

    public static ArticleResponse of(Article article){
        return ArticleResponse.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .nickname(article.getNickname())
                .date(article.getDate())
                .build();
    }
}
