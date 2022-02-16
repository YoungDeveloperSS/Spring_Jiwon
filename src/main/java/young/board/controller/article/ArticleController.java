package young.board.controller.article;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import young.board.controller.ApiResponse;
import young.board.service.article.ArticleService;
import young.board.service.article.dto.request.ArticleRequest;

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
        articleService.post(articleRequest);
        return ApiResponse.success("게시글이 저장되었습니다.");
    }

    /**
     * 게시글 수정
     */

    /**
     * 게시글 삭제
     */

    /**
     * 단건 조회
     */
    @GetMapping("/search/one")
    public ApiResponse<String> searchArticleById(Long article_id){
        articleService.searchArticleById(article_id);
        return ApiResponse.success("게시글 한 건을 조회합니다.");
    }

    /**
     * 리스트 조회
     */
    @GetMapping("/search/all")
    public ApiResponse<String> searchArticleAll(){
        articleService.searchAllArticles();
        return ApiResponse.success("게시글 전체를 조회합니다.");
    }

}
