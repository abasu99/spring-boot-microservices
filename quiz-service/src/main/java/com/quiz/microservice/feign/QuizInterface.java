package com.quiz.microservice.feign;

import com.quiz.microservice.model.QuestionWrapper;
import com.quiz.microservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/generate-quiz")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz (@RequestParam String category);

    @PostMapping("question/quiz-questions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    public ResponseEntity<HashMap<String, Integer>> getScore(@RequestBody List<Response> responses);
}
