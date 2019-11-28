package tri.lo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tri.lo.model.Answer;
import tri.lo.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/api/auth/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    private ResponseEntity<List<Answer>> listAllAnswer() {
        List<Answer> answers = answerService.findAll();
        if (answers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Void> createAnswer(@RequestBody Answer answer, UriComponentsBuilder ucBuilder) {
        answerService.save(answer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/auth/answers/{id}").buildAndExpand(answer.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
