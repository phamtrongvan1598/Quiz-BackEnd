package com.sm.ms.service;


import com.sm.ms.model.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    List<Answer> findAll();

    Optional<Answer> findById(Long id);

    void save(Answer answer);

    void remove(Long id);
}
