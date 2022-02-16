package young.board.domain.article;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ArticleCategory {
    FREE ("자유"),
    MEMBER ("멤버");
    //TODO : 추가하기

    private final String category;
}
