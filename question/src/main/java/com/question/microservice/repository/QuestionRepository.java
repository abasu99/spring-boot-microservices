package com.question.microservice.repository;

import com.question.microservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Query(value="SELECT DISTINCT q.category FROM public.question q",
            nativeQuery = true)
    public List<String> getAllCategories();

    @Query(value = "SELECT q.id FROM public.question q where q.category=:category ORDER BY q.id DESC LIMIT 10",nativeQuery = true)
    public List<Integer> getQuizQuestionsByCategory(String category);

    public List<Question> findByCategory(String category);
}
