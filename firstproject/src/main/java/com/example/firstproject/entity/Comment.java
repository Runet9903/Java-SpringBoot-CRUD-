package com.example.firstproject.entity;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DBが自動的に1ずつ増加
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column // 該当fieldをtableの属性としてMapping
    private String nickname;

    @Column // 該当fieldをtableの属性としてMapping
    private String body;

    @SneakyThrows
    public static Comment createComment(CommentDto dto, Article article) {
        if (dto.getId() != null)
            throw new IllegalAccessException("Failed to create comments! コメントのIDがないといけません");
        if (dto.getArticleId() != article.getId())
            throw new IllegalAccessException("Failed to create comments! 掲示文のIDが間違っています");

        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    @SneakyThrows
    public void patch(CommentDto dto) {
        if (this.id != dto.getId())
            throw new IllegalAccessException("Failed to edit comments! 間違ったIDが入力されました");

        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
