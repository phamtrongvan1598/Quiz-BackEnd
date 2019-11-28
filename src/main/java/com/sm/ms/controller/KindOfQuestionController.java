package com.sm.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tri.lo.model.KindOfQuestion;
import tri.lo.service.KindOfQuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/kindOfQuestions")
public class KindOfQuestionController {

    @Autowired
    private KindOfQuestionService kindOfQuestionService;

    @GetMapping
    private ResponseEntity<List<KindOfQuestion>> listAllKindOfQuestion() {
        List<KindOfQuestion> kindOfQuestions = kindOfQuestionService.findAll();
        if (kindOfQuestions.isEmpty()) {
            return new ResponseEntity<List<KindOfQuestion>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<KindOfQuestion>>(kindOfQuestions, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Void> createKindOfQuestion(@RequestBody KindOfQuestion kindOfQuestion, UriComponentsBuilder ucBuilder) {
        kindOfQuestionService.save(kindOfQuestion);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/auth/kindOfQuestions/{id}").buildAndExpand(kindOfQuestion.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<KindOfQuestion> getKindOfQuestionById(@PathVariable Long id) {
        Optional<KindOfQuestion> kindOfQuestion = kindOfQuestionService.findById(id);
        if (kindOfQuestion.isPresent()) {
            return new ResponseEntity<>(kindOfQuestion.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    private ResponseEntity<KindOfQuestion> updateKindOfQuestion(@PathVariable Long id, @RequestBody KindOfQuestion kindOfQuestion) {
        Optional<KindOfQuestion> currentKindOfQuestion = kindOfQuestionService.findById(id);
        if (currentKindOfQuestion.isPresent()) {
            currentKindOfQuestion.get().setKindOfQuestion(kindOfQuestion.getKindOfQuestion());
            currentKindOfQuestion.get().setId(kindOfQuestion.getId());
            currentKindOfQuestion.get().setQuestions(kindOfQuestion.getQuestions());

            kindOfQuestionService.save(currentKindOfQuestion.get());

            return new ResponseEntity<KindOfQuestion>(currentKindOfQuestion.get(), HttpStatus.OK);
        }

        return new ResponseEntity<KindOfQuestion>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    private ResponseEntity<KindOfQuestion> deleteKindOfQuestion(@PathVariable Long id) {

        Optional<KindOfQuestion> kindOfQuestion = kindOfQuestionService.findById(id);
        if (kindOfQuestion.isPresent()) {
            kindOfQuestionService.remove(id);
            return new ResponseEntity<KindOfQuestion>(HttpStatus.OK);
        }


        return new ResponseEntity<KindOfQuestion>(HttpStatus.NOT_FOUND);

    }
}
