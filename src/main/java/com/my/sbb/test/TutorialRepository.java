package com.my.sbb.test;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TutorialRepository extends MongoRepository<Tutorial, String> {
//    List<Tutorial> findByTitleContaining(String title);
//    List<Tutorial> findByPublished(boolean published);
}
