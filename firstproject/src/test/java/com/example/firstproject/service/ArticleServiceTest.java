package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        // 1. 予想 data
        Article a = new Article(1L, "AAAA", "1111");
        Article b = new Article(2L, "BBBB", "2222");
        Article c = new Article(3L, "CCCC", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 2. 実際の dataを取得
        List<Article> articles = articleService.index();

        // 3. 予想dataと 実際のdataを 比較して検証
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_成功() {
        // 1. 予想 data
        Long id = 1L;
        Article expected = new Article(id,"AAAA", "1111");

        // 2. 実際 data
        Article article = articleService.show(id);

        // 3. 予想dataと 実際のdataを 比較して検証
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_失敗() {
        // 1. 予想 data
        Long id = -1L;
        Article expected = null;

        // 2. 実際 data
        Article article = articleService.show(id);

        // 3. 予想dataと 実際のdataを 比較して検証
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_成功() {
        // 1. 予想 data
        String title = "DDDD";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 2. 実際 data
        Article article = articleService.create(dto);

        // 3. 予想dataと 実際のdataを 比較して検証
        assertEquals(expected.toString(), article.toString());

    }
    @Test
    @Transactional
    void create_失敗() {
        // 1. 予想 data
        Long id = 4L;
        String title = "DDDD";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        // 2. 実際 data
        Article article = articleService.create(dto);

        // 3. 予想dataと 実際のdataを 比較して検証
        assertEquals(expected,article);
    }
}