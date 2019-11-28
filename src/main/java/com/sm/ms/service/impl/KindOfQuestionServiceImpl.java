package com.sm.ms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tri.lo.model.KindOfQuestion;
import tri.lo.repository.KindOfQuestionRepository;
import tri.lo.service.KindOfQuestionService;

import java.util.List;
import java.util.Optional;

public class KindOfQuestionServiceImpl implements KindOfQuestionService {

    @Autowired
    private KindOfQuestionRepository kindOfQuestionRepository;

    @Override
    public List<KindOfQuestion> findAll() {
        return kindOfQuestionRepository.findAll();
    }

    @Override
    public Optional<KindOfQuestion> findById(Long id) {
        return kindOfQuestionRepository.findById(id);
    }

    @Override
    public void save(KindOfQuestion kindOfQuestion) {
        kindOfQuestionRepository.save(kindOfQuestion);
    }

    @Override
    public void remove(Long id) {
        kindOfQuestionRepository.deleteById(id);
    }
}
