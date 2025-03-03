package com.quiz.microservice.contoller;

import com.quiz.microservice.model.QuestionWrapper;
import com.quiz.microservice.model.Response;
import com.quiz.microservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/quiz",produces = {MediaType.APPLICATION_JSON_VALUE})
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/generate-quiz")
    public ResponseEntity<List<Integer>> getQuestionIdsForQuiz (@RequestParam String category, @RequestParam String title){    // add difficulty level also
        List<Integer> questionIds =quizService.getQuestionIds(category,title);
        return ResponseEntity.status(HttpStatus.OK).body(questionIds);
    }

    @PostMapping("/quiz-questions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestParam int quizId){
        List<QuestionWrapper> questions=quizService.getQuizQuestions(quizId);
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @PostMapping("/getScore")
    public ResponseEntity<HashMap<String, Integer>> getScorecard(@RequestBody List<Response> responses){
        HashMap<String, Integer> scorecard=quizService.getScore(responses);
        return ResponseEntity.status(HttpStatus.OK).body(scorecard);
    }
}
