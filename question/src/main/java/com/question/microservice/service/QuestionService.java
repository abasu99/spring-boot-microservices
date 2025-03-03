package com.question.microservice.service;

import com.question.microservice.entity.Question;
import com.question.microservice.entity.QuestionWrapper;
import com.question.microservice.entity.Response;

import java.util.HashMap;
import java.util.List;

public interface QuestionService {

    public List<Question> getAllQuestions();
    public Question addQuestion(Question question);
    public List<String> getCategories();
//    public List<Question> getQuizQuestionsByCategory(String category);
    public List<Question> findByCategory(String category);
    public List<Integer> getQuestionsForQuiz(String categoryName);
    public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds);
    public HashMap<String, Integer> getScore(List<Response> responses);
}
