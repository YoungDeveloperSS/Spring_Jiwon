package young.board.controller.article;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import young.board.controller.ApiResponse;
import young.board.domain.article.Article;
import young.board.service.article.ArticleService;
import young.board.service.article.dto.request.ArticleEditRequest;
import young.board.service.article.dto.request.ArticleRequest;
import young.board.service.article.dto.response.ArticleResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
public class ArticleController {
    private final ArticleService articleService;

    /**
     * 게시글 등록
     */
    @PostMapping("/post")
    public ApiResponse<String> postArticle(@RequestBody ArticleRequest articleRequest){
        articleService.postArticle(articleRequest);
        return ApiResponse.success("게시글이 저장되었습니다.");
    }

    /**
     * 게시글 수정
     */
    @PostMapping("/edit")
    public ApiResponse<String> editArticle(@RequestBody ArticleEditRequest articleEditrequest){
        articleService.editArticle(articleEditrequest);
        return ApiResponse.success("게시글이 수정되었습니다.");
    }

    /**
     * 게시글 삭제
     */
    @PostMapping("/delete")
    public ApiResponse<String> deleteArticle(@RequestParam Long article_id){
        articleService.deleteArticle(article_id);
        return ApiResponse.success("게시글이 삭제되었습니다.");
    }

    /**
     * 단건 조회
     */
    @GetMapping("/search/one")
    public ApiResponse<ArticleResponse> searchArticleById(@RequestParam Long article_id){
        return ApiResponse.success(articleService.searchArticleById(article_id));
    }

    /**
     * 리스트 조회
     */
    @GetMapping("/search/all")
    public ApiResponse<List<ArticleResponse>> searchArticleAll(){
        return ApiResponse.success(articleService.searchAllArticles());
    }

}
