package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("特定の投稿のすべてのコメントを参照")
    void findByArticleId() {
        /* Case1 : 4番、投稿のすべてのコメントを照会 */
        {
            // 1. 入力データ準備
            Long articled = 4L;

            // 2. 実データ
            List<Comment> comments = commentRepository.findByArticleId(articled);

            // 3. 予想データ
            Article article = new Article(4L, "Movie", "Comment1");
                    Comment a = new Comment(1L, article, "Name1", "Movie1");
                    Comment b = new Comment(2L, article, "Name2", "Movie2");
                    Comment c = new Comment(3L, article, "Name3", "Movie3");
                    List<Comment> expected = Arrays.asList(a,b,c);
            // 4. 比較,検証
            assertEquals(expected.toString(), comments.toString(), "4番コメントのすべてのコメントを出力!");
        }

        /* Case2 : 1番、投稿のすべてのコメントを照会 */
        {
            // 1. 入力データ準備
            Long articled = 1L;

            // 2. 実データ
            List<Comment> comments = commentRepository.findByArticleId(articled);

            // 3. 予想データ
            Article article = new Article(1L, "AAAA", "1111");
            List<Comment> expected = Arrays.asList();

            // 4. 比較,検証
            assertEquals(expected.toString(), comments.toString(), "1番コメントはコメントがない");
        }
    }

    @Test
    @DisplayName("特定のニックネームのすべてのコメント照会")
    void findByNickname() {
        /* Case 1: "Name1"のすべてのコメント照会 */
        {
            // 1. 入力データ準備
            String nickname = "Name1";

            // 2. 実データ
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 3. 予想データ
            Comment a = new Comment(1L, new Article(4L, "Movie", "Comment1"), nickname, "Movie1");
            Comment b = new Comment(4L, new Article(5L, "Sport", "Comment2"), nickname, "Soccer");
            Comment c = new Comment(7L, new Article(6L, "Food", "Comment3"), nickname, "Sushi");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 4. 比較,検証
            assertEquals(expected.toString(), comments.toString(), "Name1のすべてのコメントを出力");
        }
    }
}