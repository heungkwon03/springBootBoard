package com.my.sbb.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    //
    private final BoardService boardService;

    @GetMapping("/board-list/{type}")
    public List<Board> findBoardList(@PathVariable BoardType type) {
        //
        List<Board> boardList = boardService.findBoardList(type);

        return boardList;
    }

    @GetMapping("/board/{id}")
    public Board findBoard(@PathVariable String id) {
        //
        Board board = boardService.findBoard(id);

        return board;
    }

    @PostMapping("/add-board")
    public Board addBoard(@RequestBody BoardDTO boardDTO) {
        //
        boardService.addBoard(boardDTO);

        return boardDTO.toBoard();
    }

    @PutMapping("/update-board/{id}")
    public Board updateBoard(@PathVariable String id, @RequestBody BoardDTO boardDTO) {
        //
        boardService.updateBoard(id, boardDTO);

        return boardDTO.toBoard();
    }

    @DeleteMapping("/delete-board/{id}")
    public String deleteBoard(@PathVariable String id) {
        //
        boardService.deleteBoard(id);

        String deleteMessage = "Success";
        return deleteMessage;
    }
}
