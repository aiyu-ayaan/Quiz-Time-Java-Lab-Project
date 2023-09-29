package com.aatec.quiztime.data.room.mapper;

import com.aatec.quiztime.data.retrofit.model.QuizModel;
import com.aatec.quiztime.data.room.model.QuizRoomModel;
import com.aatec.quiztime.utils.EntityMapper;

import java.util.stream.Collectors;

import javax.inject.Inject;

public class QuizMapper implements EntityMapper<QuizModel, QuizRoomModel> {

    @Inject
    public QuizMapper() {
    }

    @Override
    public QuizRoomModel mapFromEntity(QuizModel entity) {
        var quizModel = new QuizRoomModel();
        var data = entity.getQuizModels();
        quizModel.setCategory((data.get(0).getCategory() == null) ? "Unknown" : data.get(0).getCategory());
        quizModel.setDifficulty(
                (data.get(0).getDifficulty() == null) ? "Unknown" : data.get(0).getDifficulty()
        );
        quizModel.setQuestions(
                data.stream().map(v -> new QuizRoomModel.Questions(
                        v.getQuestion(),
                        v.getCorrectAnswer(),
                        v.getIncorrectAnswers(),
                        v.isAnsweredCorrectly(),
                        v.getUserAnswer()
                )).collect(Collectors.toList())
        );
        return quizModel;
    }

    @Override
    public QuizModel mapToEntity(QuizRoomModel domainModel) {
        var quizModel = new QuizModel();
        var list = domainModel.getQuestions().stream().map(v -> new QuizModel.QuizData(
                domainModel.getCategory(),
                "",
                domainModel.getDifficulty(),
                v.getQuestion(),
                v.getCorrectAnswer(),
                v.getIncorrectAnswers(),
                v.isAnsweredCorrectly(),
                v.getUserAnswer()
        )).collect(Collectors.toList());
        quizModel.setQuizModels(list);
        return quizModel;
    }
}
