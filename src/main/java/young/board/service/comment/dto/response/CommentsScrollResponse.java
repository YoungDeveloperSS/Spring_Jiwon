package young.board.service.comment.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import young.board.domain.comment.Comment;
import young.board.util.common.ScrollPaginationCollection;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentsScrollResponse {

    private static final long LAST_CURSOR = -1L;

    private List<CommentResponse> contents = new ArrayList<>();
    private long totalElements;
    private long nextCursor;

    private CommentsScrollResponse(List<CommentResponse> contents, long totalElements, long nextCursor) {
        this.contents = contents;
        this.totalElements = totalElements;
        this.nextCursor = nextCursor;
    }

    public static CommentsScrollResponse of(ScrollPaginationCollection<Comment> commentsScroll, long totalElements) {
        if (commentsScroll.isLastScroll()) {
            return CommentsScrollResponse.newLastScroll(commentsScroll.getCurrentScrollItems(), totalElements);
        }
        return CommentsScrollResponse.newScrollHasNext(commentsScroll.getCurrentScrollItems(),
                totalElements, commentsScroll.getNextCursor().getId());
    }

    private static CommentsScrollResponse newLastScroll(List<Comment> comments, long totalElements) {
        return newScrollHasNext(comments, totalElements, LAST_CURSOR);
    }

    private static CommentsScrollResponse newScrollHasNext(List<Comment> comments, long totalElements, long nextCursor) {
        return new CommentsScrollResponse(getContents(comments), totalElements, nextCursor);
    }

    private static List<CommentResponse> getContents(List<Comment> comments) {
        List<CommentResponse> commentsRespons = new ArrayList<>();
        for(int i=0;i<comments.size();i++){
            commentsRespons.add(CommentResponse.of(comments.get(i)));
        }
        return commentsRespons;
    }
}