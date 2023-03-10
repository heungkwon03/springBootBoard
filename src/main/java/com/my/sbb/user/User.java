package com.my.sbb.user;

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
public class User {
    //
    @Id
    private String id;
    private String name;
    private String email;
    private String passWord;
    private LocalDateTime createDate;

}
