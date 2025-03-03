package com.question.microservice.controller;

import com.question.microservice.entity.Question;
import com.question.microservice.entity.QuestionWrapper;
import com.question.microservice.entity.Response;
import com.question.microservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/question",produces = {MediaType.APPLICATION_JSON_VALUE})
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/question-list")
    public ResponseEntity<List<Question>> getAllQuestions(){
        List<Question> allQuestionsList= questionService.getAllQuestions();
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(allQuestionsList);
    }

    @GetMapping("/question-categories")
    public ResponseEntity<List<String>> getCategories(){
        List<String> categories= questionService.getCategories();
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(categories);
    }

//    @GetMapping("/quiz-questions")
//    public ResponseEntity<List<Question>> getQuizQuestionsByCategory(@RequestParam String category){
//        List<Question> quizQuestionsByCategory= questionService.getQuizQuestionsByCategory(category);
//        return  ResponseEntity
//                .status(HttpStatus.OK)
//                .body(quizQuestionsByCategory);
//    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@RequestParam String category){
        List<Question> questionsByCategory= questionService.findByCategory(category);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(questionsByCategory);
    }

    @PostMapping("/add-question")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        Question responsequestion= questionService.addQuestion(question);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(responsequestion);
    }

    @GetMapping("/generate-quiz")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz (@RequestParam String category){    // add difficulty level also
        List<Integer> questionIds =questionService.getQuestionsForQuiz(category);
        return ResponseEntity.status(HttpStatus.OK).body(questionIds);
    }

    @PostMapping("/quiz-questions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
//        System.out.println(environment.getProperty("local.server.port"));
        List<QuestionWrapper> questionWrappers = questionService.getQuestionsFromId(questionIds);
        return ResponseEntity.status(HttpStatus.OK).body(questionWrappers);
    }

    @PostMapping("/getScore")
    public ResponseEntity<HashMap<String, Integer>> getScore(@RequestBody List<Response> responses)
    {
        HashMap<String, Integer> scorecard= questionService.getScore(responses);
        return ResponseEntity.status(HttpStatus.OK).body(scorecard);
    }

//    @GetMapping("/quiz/create-quiz")
//    public ResponseEntity<Quiz> createQuiz(@RequestParam String category,@RequestParam String title){
//        Quiz newQuiz= quizService.createQuiz(category,title);
//        return  ResponseEntity
//                .status(HttpStatus.OK)
//                .body(newQuiz);
}
