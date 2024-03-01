package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        /*// 1. コメント照会
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 2. Entity -> Dto
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }*/

        // 3. 結果返還
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 掲示文の照会及び例外発生
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Failed to create comments!" +
                        "No target posts"));

        // 2. Create comment Entity
        Comment comment = Comment.createComment(dto, article);

        // 3. comment Entity -> DB
        Comment created = commentRepository.save(comment);

        // 4. DTOに変換して返却
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. コメント照会及び例外発生
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Failed to edit comments!" + "No target comments"));

        // 2. コメント修正
        target.patch(dto);

        // 3. DBへの更新
        Comment updated = commentRepository.save(target);

        // 4. Comment Entity -> DTO
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 1. コメント照会及び例外発生
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Failed to delete the comments!" + "No target comments"));

        // 2. コメント削除
        commentRepository.delete(target);

        // 3. 削除コメント -> DTO
        return CommentDto.createCommentDto(target);
    }
}
