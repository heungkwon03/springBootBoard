package com.my.sbb.board;

import com.my.sbb.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    //
    @Id
    private String id;
    private BoardType type;
    private String no;
    private String title;
    private String content;
    //User name 으로 변경할 것
    private String name;
    private LocalDateTime createDate;

}
