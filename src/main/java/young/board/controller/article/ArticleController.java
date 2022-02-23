package young.board.controller.article;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import young.board.controller.ApiResponse;
import young.board.domain.article.Article;
import young.board.service.article.ArticleService;
import young.board.service.article.dto.request.ArticleEditRequest;
import young.board.service.article.dto.request.ArticleRequest;
import young.board.service.article.dto.response.ArticleResponse;

import java.io.IOException;
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
    public ApiResponse<String> postArticle(@RequestPart MultipartFile multipartFile,
                                           @RequestParam String content, @RequestParam String date,
                                           @RequestParam String nickname, @RequestParam String title) throws IOException {

        articleService.postArticle(new ArticleRequest(title,content,nickname,date,multipartFile));
        return ApiResponse.success("게시글이 저장되었습니다.");
    }

    @PostMapping("/post/image")
    public ApiResponse<String> postImageArticle(@RequestPart MultipartFile multipartFile) throws IOException {
        articleService.postImageArticle(multipartFile);
        return ApiResponse.success("이미지가 저장되었습니다.");
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

    /**
     * 추천
     */
    @PostMapping("/recommendation/set")
    public ApiResponse<String> setRecommendation(@RequestParam Long article_id, @RequestParam Long user_id){
        articleService.setRecommendation(article_id,user_id);
        return ApiResponse.success("추천이 완료되었습니다.");
    }

    /**
     * 추천 취소
     */
    @PostMapping("/recommendation/unSet")
    public ApiResponse<String> unsetRecommendation(@RequestParam Long article_id, @RequestParam Long user_id) {
        articleService.unsetRecommendation(article_id, user_id);
        return ApiResponse.success("추천이 취소되었습니다.");
    }
}
