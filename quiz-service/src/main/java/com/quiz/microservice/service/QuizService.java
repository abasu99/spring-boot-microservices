package com.quiz.microservice.service;

import com.quiz.microservice.feign.QuizInterface;
import com.quiz.microservice.model.QuestionWrapper;
import com.quiz.microservice.model.Quiz;
import com.quiz.microservice.model.Response;
import com.quiz.microservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;

    public List<Integer> getQuestionIds(String category,String title){
        List<Integer> questionIds = quizInterface.getQuestionsForQuiz(category).getBody();
        Quiz quiz = new Quiz();
        quiz.setQuizTitle(title);
        quiz.setQuestionIds(questionIds);
        quizRepository.save(quiz);

        return questionIds;
    }

    public List<QuestionWrapper> getQuizQuestions(int quizId){
        Quiz quiz = quizRepository.findById(quizId).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        return quizInterface.getQuestionsFromId(questionIds).getBody();
    }

    public HashMap<String, Integer> getScore(List<Response> responses){
        return quizInterface.getScore(responses).getBody();
    }
}
