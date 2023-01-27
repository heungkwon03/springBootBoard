package com.my.sbb.test;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TutorialDTO {

    private String id;
    private String title;
    private String content;
    private boolean published;

    public Tutorial toTutorial() {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle(title);
        tutorial.setContent(content);
        return tutorial;
    }
    @Override
    public String toString() {
        return "TutorialDTO [id=" + id + ", title=" + title + ", desc=" + content + ", published=" + published + "]";
    }

}
