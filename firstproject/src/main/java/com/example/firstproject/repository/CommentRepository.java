package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 特定の投稿のすべてのコメントを参照
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId",
    nativeQuery = true) //
    List<Comment> findByArticleId(Long articleId);

    // 特定のニックネームのすべてのコメント照会
    List<Comment> findByNickname(String nickname);
}
