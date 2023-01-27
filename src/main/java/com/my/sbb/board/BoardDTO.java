package com.my.sbb.board;

import com.my.sbb.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    //
    @Id
    private String id;
    private BoardType type;
    private String no;
    private String title;
    private String content;
    private String name;
    private LocalDateTime createDate;

    public Board toBoard() {
        //
        Board board = new Board();
        board.setType(type);
        board.setTitle(title);
        board.setContent(content);
        board.setName(name);
        board.setCreateDate(LocalDateTime.now());

        return board;
    }
}
