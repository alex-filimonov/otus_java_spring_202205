package ru.otus.spring.spring04.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring04.dto.AnswerDto;
import ru.otus.spring.spring04.model.Answer;
import ru.otus.spring.spring04.model.Question;
import ru.otus.spring.spring04.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions(List<String> lines) {
        return questionRepository.getQuestionByStringList(lines);
    }

    @Override
    public Answer getAnswerFromQuestionAndNumberAnswer(Question question, int number) {
        return questionRepository.getAnswerFromQuestionAndNumberAnswer(question, number);
    }

}
