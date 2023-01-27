package com.my.sbb.comment;

import com.my.sbb.board.Board;
import com.my.sbb.board.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CommentService {
    //
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentService(
            CommentRepository commentRepository,
            BoardRepository boardRepository
    ) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
    }

    public List<Comment> findCommentList(Board board) {
        //
        List<Comment> commentList = commentRepository.findAll();
        Optional<Board> targetBoard = boardRepository.findById(board.getId());

        List<Comment> boardComment = commentList.stream().filter(c -> c.getBoard().getId().equals(targetBoard.get().getId())).sorted(Comparator.comparing(Comment::getCreateDate)).collect(Collectors.toList());

        return boardComment;
    }

    public void addComment(CommentDTO commentDTO) {
        //
        Comment comment = commentDTO.toComment();

        commentRepository.save(comment);
    }

    public void updateComment(String id, CommentDTO commentDTO) {
        //
        Optional<Comment> targetComment = commentRepository.findById(id);

        if (targetComment.isPresent()) {
            Comment changeComment = targetComment.get();
            changeComment.setContent(commentDTO.getContent());

            commentRepository.save(changeComment);
        } else {
            throw new NoSuchElementException("Cannot find the comment");
        }
    }

    public void deleteComment(String id) {
        //
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isPresent()) {
            commentRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Cannot find the comment");
        }
    }
}
