package com.sm.ms.controller;

import com.sm.ms.form.response.ResponseMessage;
import com.sm.ms.model.KindOfQuestion;
import com.sm.ms.model.User;
import com.sm.ms.security.jwt.JwtAuthTokenFilter;
import com.sm.ms.security.jwt.JwtProvider;
import com.sm.ms.security.services.UserDetailsServiceImpl;
import com.sm.ms.service.KindOfQuestionService;
import com.sm.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/kindOfQuestions")
public class KindOfQuestionController {
    @Autowired
    UserService userService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtAuthTokenFilter authenticationJwtTokenFilter;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private KindOfQuestionService kindOfQuestionService;
//    @RequestMapping(value = "/create-kindOfQuestions", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
//    public ResponseEntity<KindOfQuestion> createNote(@RequestBody CreateNoteForm createNoteForm) {
//        User user = userService.getUserByAuth();
//        Note note = new Note(createNoteForm.getTitle(), createNoteForm.getContent());
//        note.setWriter(user);
//        noteService.save(note);
//        return new ResponseEntity<Note>(note, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
//    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) {
//        Note note = noteService.findById(id);
//        return new ResponseEntity<Note>(note, HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = "/notes/all", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
//    public ResponseEntity<List<Note>> listNoteByUser() {
//        User user = userService.getUserByAuth();
//        List<Note> notes = noteService.findAllByUsername(user.getUsername());
//        if (notes.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(notes, HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
//    public ResponseEntity<?> deleteNote(@PathVariable("id") Long id) {
//        try {
//            User user = userService.getUserByAuth();
//            User writer = noteService.findById(id).getWriter();
//            if (user.getId().equals(writer.getId())) {
//                noteService.remove(id);
//                return new ResponseEntity<>(new ResponseMessage("Delete Note successfully"), HttpStatus.OK);
//            }
//            return new ResponseEntity<>(new ResponseMessage("You are not writer of this note"), HttpStatus.FORBIDDEN);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @RequestMapping(value = "/note/{id}", method = RequestMethod.PUT)
//    @PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
//    public ResponseEntity<?> editNote(@PathVariable("id") Long id, @RequestBody Note note) {
//        try {
//            User user = userService.getUserByAuth();
//            User writer = noteService.findById(id).getWriter();
//            Note expectedNote = noteService.findById(id);
//            if (user.getId().equals(writer.getId())) {
//                noteService.edit(expectedNote, note);
//                noteService.save(expectedNote);
//                return new ResponseEntity<>(new ResponseMessage("Update Note successfully"), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(new ResponseMessage("You are not writer of this note"), HttpStatus.FORBIDDEN);
//            }
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.NOT_FOUND);
//        }
//    }
//@RequestMapping(value = "/create-note", method = RequestMethod.POST)
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
