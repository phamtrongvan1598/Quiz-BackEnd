package tri.lo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String question;

    @OneToMany(targetEntity = Answer.class, mappedBy = "question")
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "kindOfQuestion_id")
    private KindOfQuestion kindOfQuestion;

    public Question() {
    }

    public Question(String question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KindOfQuestion getKindOfQuestion() {
        return kindOfQuestion;
    }

    public void setKindOfQuestion(KindOfQuestion kindOfQuestion) {
        this.kindOfQuestion = kindOfQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


}
