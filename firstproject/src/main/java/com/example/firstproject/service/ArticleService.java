package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository; // 投稿 Repository オブジェクトの注入

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> Entity
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article, toString());

        // 2. Target
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 誤った要求処理
        if (target == null || id != article.getId()) {
            // 400!
            log.info("Invalid request! id: {}, article: {}", id, article.toString());
            return null;
        }

        // 4. Update, 正常応答処理(200)
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
        }

    public Article delete(Long id) {
        // 1. 対象探し
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 誤った要求を処理
        if (target == null) {
            return null;
        }

        // 3. 対象を削除
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {

        // 1. dto束 -> Entity束
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // 2. Entity束 -> DBに保存
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 3. 強制例外発生させる
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("決済失敗!"));

        // 4. 結果値の返還
        return articleList;

    }
}




