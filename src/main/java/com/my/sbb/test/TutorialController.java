package com.my.sbb.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TutorialController {

    private final TutorialService tutorialService;

    @GetMapping("/tutorials")
    public List<Tutorial> findTutorialList() {

        List tutorialLists = tutorialService.findTutorialList();

        return tutorialLists;
    }

    @GetMapping("/tutorials/{id}")
    public Tutorial findTutorial(@PathVariable("id") String id) {

        Tutorial tutorial = tutorialService.findTutorial(id);

        return tutorial;
    }

    @PostMapping("/add-tutorial")
    public Tutorial addTutorial(@RequestBody TutorialDTO tutorialDto) {

        tutorialService.addTutorial(tutorialDto);

        return tutorialDto.toTutorial();
    }

    @PutMapping("/update-tutorial/{id}")
    public Tutorial updateTutorial(@PathVariable("id") String id, @RequestBody TutorialDTO tutorialDto) {

        tutorialService.updateTutorial(id, tutorialDto);

        return tutorialDto.toTutorial();
    }

    @DeleteMapping("/tutorials/{id}")
    public String deleteTutorial(@PathVariable("id") String id) {

        tutorialService.deleteTutorial(id);

        String deleteMessage = "Success";
        return deleteMessage;
    }

//    @DeleteMapping("/tutorials")
//    public ResponseEntity<HttpStatus> deleteAllTutorials() {
//        try {
//            tutorialRepository.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/tutorials/published")
//    public ResponseEntity<List<Tutorial>> findByPublished() {
//        try {
//            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
//
//            if (tutorials.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}