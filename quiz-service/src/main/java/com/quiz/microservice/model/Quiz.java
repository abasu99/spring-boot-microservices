package com.quiz.microservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
//@SequenceGenerator(name="id", initialValue=411)
public class Quiz {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    public String quizTitle;

    @ElementCollection
    private List<Integer> questionIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
    }
}
