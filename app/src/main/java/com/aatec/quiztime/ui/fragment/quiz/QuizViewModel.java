package com.aatec.quiztime.ui.fragment.quiz;

import android.util.Log;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.aatec.quiztime.data.retrofit.QuizRepository;
import com.aatec.quiztime.data.retrofit.model.QuizModel;
import com.aatec.quiztime.data.room.QuizRoomRepository;
import com.aatec.quiztime.data.room.mapper.QuizMapper;
import com.aatec.quiztime.ui.fragment.start_quiz.StartQuizFragment;

import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class QuizViewModel extends ViewModel {

    private final QuizRepository quizRepository;

    private final SavedStateHandle state;

    private final QuizRoomRepository roomRepository;
    private final QuizMapper quizMapper;

    private int points = 0;


    @Inject
    public QuizViewModel(QuizRepository quizRepository,
                         QuizRoomRepository roomRepository,
                         QuizMapper quizMapper,
                         SavedStateHandle state
    ) {
        this.quizRepository = quizRepository;
        this.roomRepository = roomRepository;
        this.quizMapper = quizMapper;
        this.state = state;
    }

    StartQuizFragment.QuizDetailModel getQuizDetails() {
        return state.get("quizDetails");
    }

    void loadQuizFromApi(
            Consumer<QuizModel> onSuccess,
            Consumer<String> onError
    ) {
        var quizDetails = getQuizDetails();
        quizRepository.getQuiz(
                quizDetails.getDifficulty(),
                quizDetails.getCategory(),
                onSuccess,
                onError
        );
    }


    public void insertData(List<QuizModel.QuizData> data, Consumer<Exception> state) {
        try {
            var quizModel = new QuizModel(data);
            var quizRoomModel = quizMapper.mapFromEntity(quizModel);
            Log.d("TAG", "insertData: " + quizRoomModel.toString());
            roomRepository.insertQuiz(quizRoomModel);
            state.accept(null);
        } catch (Exception e) {
            state.accept(e);
        }
    }

    public int getPoints() {
        return points;
    }

    public void incrementPoints() {
        points++;
    }

}
