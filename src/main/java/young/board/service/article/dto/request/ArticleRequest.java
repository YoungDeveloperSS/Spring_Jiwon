package young.board.service.article.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleRequest {
    private String title;

    private String content;

    private String nickname;

    private String date;

    public ArticleRequest(String title, String content, String nickname, String date, MultipartFile multipartFile) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.date = date;
    }
}
