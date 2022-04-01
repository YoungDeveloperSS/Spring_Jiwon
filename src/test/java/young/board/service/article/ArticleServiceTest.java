package young.board.service.article;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; //Junit5 어노테이션
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import young.board.domain.article.Article;
import young.board.domain.article.ArticleRecommendation;
import young.board.domain.article.ArticleRepository;
import young.board.service.article.dto.response.ArticleResponse;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest //실제 db연결 테스트
@Transactional //자동 롤백
class ArticleServiceTest {
    @Autowired //생성자 주입은 Autowired로 수행
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;

    @BeforeEach
    void beforeEach(){
        articleRepository.save(Article.of("title1","content1","nickname1","2022-03-02","http://www.naver.com1"));
        articleRepository.save(Article.of("title2","content2","nickname2","2022-03-03","http://www.naver.com2"));
        articleRepository.save(Article.of("title3","content3","nickname3","2022-03-04","http://www.naver.com3"));
    }

    /**
     * id로 게시글 찾기
     * null값일 때 오류 발생하는지 확인
     */
    @Test
    void searchArticleById() {
        //given

        //when
        ArticleResponse articleResponse = articleService.searchArticleById(1l);

        //then
        assertThat(articleResponse.getTitle()).isEqualTo("title1"); //Junit5스타일
        assertThatThrownBy(() -> articleService.searchArticleById(15l))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게시글이 존재하지 않습니다.");
    }

    @Test
    void postArticle() {
    }

    @Test
    void postImageArticle() {
    }

    @Test
    void editArticle() {
    }

    @Test
    void deleteArticle() {
    }

    @Test
    void setRecommendation() {
        //1. 한번도 추천X - 추천실행(성공), 추천취소(실패)
        //2. 이전에 추천O , 현재 추천O - 추천실행(실패), 추천취소(성공)
        //3. 이전에 추천O , 현재 추천X - 추천실행(성공), 추천취소(실패)
//        articleRepository.save(Article.of2("한번도추천X","content1","nickname3","2022-03-04","http://www.naver.com3",false));
//        articleRepository.save(Article.of2("이전에추천O,현재추천O","content2","nickname3","2022-03-04","http://www.naver.com3",true));
//        articleRepository.save(Article.of2("이전에추천O,현재추천O","content3","nickname3","2022-03-04","http://www.naver.com3",false));
//        articleRepository.save(Article.of2("이전에추천O,현재추천X","content4","nickname3","2022-03-04","http://www.naver.com3",false));
//        articleRepository.save(Article.of2("이전에추천O,현재추천X","content5","nickname3","2022-03-04","http://www.naver.com3",false));
//        ArticleRecommendation.of(1l,1l);
        //given

        //when

        //then
    }

    @Test
    void unsetRecommendation() {
    }
}