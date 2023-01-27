package com.my.sbb.test;

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
public class TutorialService {

    private final TutorialRepository tutorialRepository;

    public TutorialService(
        TutorialRepository tutorialRepository
    ) {
        this.tutorialRepository = tutorialRepository;
    }

    public void addTutorial(TutorialDTO tutorialDto){

        if(tutorialDto.getTitle() == null) {
            throw new NoSuchElementException("title cannot be empty. ");
        }
        if(tutorialDto.getContent() == null) {
            throw new NoSuchElementException("content cannot be empty. ");
        }

        Tutorial tutorial = tutorialDto.toTutorial();
        tutorialDto.setPublished(true);
        tutorialRepository.save(tutorial);
    }

    public List<Tutorial> findTutorialList() {

        List<Tutorial> tutorialList;
        tutorialList = tutorialRepository.findAll();

        return tutorialList.stream().sorted(Comparator.comparing(Tutorial::getId)).collect(Collectors.toList());
    }

    public Tutorial findTutorial(String id) {

        if(id == null) {
            throw new NoSuchElementException("Cannot find the tutorial");
        }

        Optional<Tutorial> tutorial = tutorialRepository.findById(id);

        return tutorial.get();
    }

    public void updateTutorial(String id, TutorialDTO tutorialDto) {
        Optional<Tutorial> targetTutorial = tutorialRepository.findById(id);

        if(targetTutorial.isPresent()) {
            Tutorial changeTutorial = targetTutorial.get();
            changeTutorial.setTitle(tutorialDto.getTitle());
            changeTutorial.setContent(tutorialDto.getContent());
            changeTutorial.setPublished(tutorialDto.isPublished());

            if(changeTutorial.getTitle() == null) {
                throw new NoSuchElementException("title cannot be empty");
            }
            if(changeTutorial.getContent() == null) {
                throw new NoSuchElementException("content cannot be empty");
            }
            tutorialRepository.save(changeTutorial);
        } else {
            throw new NoSuchElementException("Cannot find tutorial");
        }
    }

    public void deleteTutorial(String id) {

        Optional<Tutorial> tutorial = tutorialRepository.findById(id);

        if(tutorial.isPresent()) {
            tutorialRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Cannot find the tutorial");
        }
    }
}
