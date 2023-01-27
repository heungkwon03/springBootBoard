package com.my.sbb.comment;

import com.my.sbb.board.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentContoller {
    //
    private final CommentService commentService;

    @GetMapping("/comment-list")
    public List<Comment> findCommentList(@RequestBody Board board) {
        //
        List<Comment> commentList = commentService.findCommentList(board);

        return commentList;
    }

    @PostMapping("/add-comment")
    public Comment addComment(@RequestBody CommentDTO commentDTO) {
        //
        commentService.addComment(commentDTO);

        return commentDTO.toComment();
    }

    @PutMapping("/update-comment/{id}")
    public Comment updateComment(@PathVariable String id, @RequestBody CommentDTO commentDTO) {
        //
        commentService.updateComment(id, commentDTO);

        return commentDTO.toComment();
    }

    @DeleteMapping("/delete-comment/{id}")
    public String deleteComment(@PathVariable String id) {
        //
        commentService.deleteComment(id);

        String deleteMessage = "Success";
        return deleteMessage;
    }
}
