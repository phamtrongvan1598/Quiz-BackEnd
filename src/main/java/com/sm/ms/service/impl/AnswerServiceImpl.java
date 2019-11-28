package com.sm.ms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tri.lo.model.Answer;
import tri.lo.repository.AnswerRepository;
import tri.lo.service.AnswerService;

import java.util.List;
import java.util.Optional;

public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Optional<Answer> findById(Long id) {
        return answerRepository.findById(id);
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void remove(Long id) {
        answerRepository.deleteById(id);
    }
}
