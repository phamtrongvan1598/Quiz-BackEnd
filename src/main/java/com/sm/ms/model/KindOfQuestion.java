package com.sm.ms.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class KindOfQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String kindOfQuestion;

    @OneToMany(targetEntity = Question.class)
    private List<Question> questions;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User writer;
//
//    public User getWriter() {
//        return writer;
//    }
//
//    public void setWriter(User writer) {
//        this.writer = writer;
//    }
//

    public KindOfQuestion() {
    }

    public KindOfQuestion(String kindOfQuestion) {
        this.kindOfQuestion = kindOfQuestion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKindOfQuestion() {
        return kindOfQuestion;
    }

    public void setKindOfQuestion(String kindOfQuestion) {
        this.kindOfQuestion = kindOfQuestion;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
