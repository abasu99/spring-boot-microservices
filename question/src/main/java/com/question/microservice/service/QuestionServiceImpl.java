package com.question.microservice.service;

import com.question.microservice.entity.Question;
import com.question.microservice.entity.QuestionWrapper;
import com.question.microservice.entity.Response;
import com.question.microservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);

    }

    @Override
    public List<String> getCategories() {
        return questionRepository.getAllCategories();
    }

//    @Override
//    public List<Question> getQuizQuestionsByCategory(String category) {
//        return questionRepository.getQuizQuestionsByCategory(category);
//    }

    @Override
    public List<Question> findByCategory(String category) {
        return questionRepository.findByCategory(category);
    }
    
    public List<Integer> getQuestionsForQuiz(String categoryName) {
        return questionRepository.getQuizQuestionsByCategory(categoryName);
    }
    
    public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            questions.add(questionRepository.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestion_title());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return wrappers;
    }

    public HashMap<String, Integer> getScore(List<Response> responses) {
        HashMap<String, Integer> scorecard = new HashMap<>();
        scorecard.put("correctAnswers",0);
        scorecard.put("wrongAnswers",0);
        scorecard.put("unattempted",0);

        for(Response response : responses){
            Question question = questionRepository.findById(response.getId()).get();
            if(response.getResponse().isBlank())
                scorecard.put("unattempted",scorecard.get("unattempted")+1);
            else if(response.getResponse().equals(question.getCorrect_answer()))
                scorecard.put("correctAnswers",scorecard.get("correctAnswers")+1);
            else if(!response.getResponse().equals(question.getCorrect_answer()))
                scorecard.put("wrongAnswers",scorecard.get("wrongAnswers")+1);
        }
        scorecard.put("totalQuestions",responses.size());
        scorecard.put("score",scorecard.get("correctAnswers")*10);
        return scorecard;
    }

}
