package young.board.service.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import young.board.domain.article.Article;
import young.board.domain.article.ArticleRepository;
import young.board.service.article.dto.request.ArticleEditRequest;
import young.board.service.article.dto.request.ArticleRequest;
import young.board.service.article.dto.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

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
}
