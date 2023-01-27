package com.my.sbb.test;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Document(collection = "tutorials")
public class Tutorial {
    @Id
    private String id;
    private String title;
    private String content;
    private boolean published;

    public Tutorial(String title, String content, boolean published) {
        this.title = title;
        this.content = content;
        this.published = published;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + title + ", desc=" + content + ", published=" + published + "]";
    }
}
