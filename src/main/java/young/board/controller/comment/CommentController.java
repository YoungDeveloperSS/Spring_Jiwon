package young.board.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import young.board.controller.ApiResponse;
import young.board.service.comment.CommentService;
import young.board.service.comment.dto.request.CommentEditRequest;
import young.board.service.comment.dto.request.CommentRequest;
import young.board.service.comment.dto.response.CommentResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    /**
     * 댓글 등록
     */
    @PostMapping("/post")
    public ApiResponse<String> postComment(@RequestBody CommentRequest commentRequest){
        commentService.postComment(commentRequest);
        return ApiResponse.success("댓글이 저장되었습니다.");
    }

    /**
     * 댓글 수정
     */
    @PostMapping("/edit")
    public ApiResponse<String> editComment(@RequestBody CommentEditRequest commentEditrequest){
        commentService.editComment(commentEditrequest);
        return ApiResponse.success("댓글이 수정되었습니다.");
    }

    /**
     * 댓글 삭제
     */
    @PostMapping("/delete")
    public ApiResponse<String> deleteComment(@RequestParam Long comment_id){
        commentService.deleteComment(comment_id);
        return ApiResponse.success("댓글이 삭제되었습니다.");
    }

    /**
     * 댓글 조회
     */
    @GetMapping("/list")
    public ApiResponse<List<CommentResponse>> searchAllCommentsByArticleId(@RequestParam Long article_id){
        return ApiResponse.success(commentService.searchAllCommentsByArticleId(article_id));
    }
}
