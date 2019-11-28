package com.sm.ms.service;

import tri.lo.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    List<Question> findAll();

    Optional<Question> findById(Long id);

    void save(Question question);

    void remove(Long id);
}
