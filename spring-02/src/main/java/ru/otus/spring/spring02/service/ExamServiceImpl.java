package ru.otus.spring.spring02.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring02.config.AppConfig;
import ru.otus.spring.spring02.controller.ExamController;
import ru.otus.spring.spring02.controller.ExamControllerImpl;
import ru.otus.spring.spring02.dto.UserDto;
import ru.otus.spring.spring02.enums.ExamResult;
import ru.otus.spring.spring02.model.Question;
import ru.otus.spring.spring02.model.User;
import ru.otus.spring.spring02.model.UserAnswer;
import ru.otus.spring.spring02.view.ExamView;

import java.util.List;

@Service
@Data
public class ExamServiceImpl implements ExamService {

    private ExamView examView;
    private ExamController examController;
    private ExamDataService examDataService;
    private AppConfig appConfig;
    private CSVResourceService csvResourceService;

    ExamServiceImpl(ExamView examView, ExamController examController, ExamDataService examDataService, AppConfig appConfig, CSVResourceService csvResourceService) {
        this.examView = examView;
        this.examController = examController;
        this.examDataService = examDataService;
        this.examController.setExamDataService(this.examDataService);
        this.appConfig = appConfig;
        this.csvResourceService = csvResourceService;
    }

    public void start() {
        List<Question> questionList = examDataService.getAllQuestions(
                csvResourceService.getLineListByResourceStream(true, csvResourceService.getCSVResourceStream()));

        examController.userSet(examView.userSet());
        examView.startExamMessage();
        questionList.forEach(question -> {
            examController.userAnswer(examView.userAnswerForm(question));
        });
        examView.endExamMessage();
        User user = examDataService.getUser();
        List<UserAnswer> userAnswers = examDataService.getUserAnswers();
        ExamResult examResult = examDataService.getResultExam(userAnswers, appConfig.getMinCorrectAnswers());
        examView.resultExamMessage(user.getName(), examResult);
    }

}
