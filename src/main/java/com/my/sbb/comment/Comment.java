package com.my.sbb.comment;

import com.my.sbb.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    //
    @Id
    private String id;
    private Board board;
    private String content;
    //User name 으로 변경
    private String name;
    private LocalDateTime createDate;
}
