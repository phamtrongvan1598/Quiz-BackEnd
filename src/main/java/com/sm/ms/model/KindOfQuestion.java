package tri.lo.model;

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
