package young.board.service.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import young.board.domain.article.Article;
import young.board.domain.article.ArticleRecommendation;
import young.board.domain.article.ArticleRecommendationRepository;
import young.board.domain.article.ArticleRepository;
import young.board.domain.article.repository.ArticleRecommendationRepositoryCustom;
import young.board.domain.user.User;
import young.board.domain.user.UserRepository;
import young.board.service.article.dto.request.ArticleEditRequest;
import young.board.service.article.dto.request.ArticleRequest;
import young.board.service.article.dto.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleRecommendationRepository articleRecommendationRepository;

    @Transactional(readOnly = true)
    public ArticleResponse searchArticleById(Long article_id){
        Article article = articleRepository.findByArticleId(article_id);
        if(article == null){
            throw new IllegalStateException("게시글이 존재하지 않습니다.");
        }
        return ArticleResponse.of(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> searchAllArticles(){
        List<Article> articles = articleRepository.findArticleAll();
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            articleResponses.add(ArticleResponse.of(article));
        }
        return  articleResponses;
    }

    @Transactional
    public Long postArticle(ArticleRequest articleRequest){
        Article article = Article.of(articleRequest.getTitle(), articleRequest.getContent(), articleRequest.getNickname(), articleRequest.getDate());
        articleRepository.save(article);
        return article.getId();
    }

    @Transactional
    public Long editArticle(ArticleEditRequest articleEditrequest){
        Article article = articleRepository.findByArticleId(articleEditrequest.getId());
        article.setArticle(articleEditrequest.getTitle(), articleEditrequest.getContent(), articleEditrequest.getNickname(), articleEditrequest.getDate());
        return article.getId();
    }

    @Transactional
    public Long deleteArticle(Long article_id){
        Article article = articleRepository.findByArticleId(article_id);
        article.unSetIsUsing();
        return article.getId();
    }

    @Transactional
    public void setRecommendation(Long article_id, Long user_id){
        ArticleRecommendation articleRecommendation = articleRecommendationRepository.findByArticleIdAndUserId(article_id, user_id);
        if(articleRecommendation == null){
            Article article = articleRepository.findByArticleId(article_id);
            User user = userRepository.findByUserId(user_id);
            articleRecommendation = ArticleRecommendation.of(article, user);
            articleRecommendationRepository.save(articleRecommendation);
        }
        else{
            if(articleRecommendation.isUsing()){
                throw new IllegalStateException("이미 추천한 게시글입니다.");
            }
            else{
                articleRecommendation.setRecommendation();
            }
        }
    }

    @Transactional
    public void unsetRecommendation(Long article_id, Long user_id){
        ArticleRecommendation articleRecommendation = articleRecommendationRepository.findByArticleIdAndUserId(article_id, user_id);
        if(articleRecommendation == null) {
            throw new IllegalStateException("이전에 추천하지 않은 게시글입니다.");
        }
        else{
            if(articleRecommendation.isUsing()){
                articleRecommendation.unsetRecommendation();
            }
            else{
                throw new IllegalStateException("이미 추천 해제한 게시글입니다.");
            }
        }
    }
}
