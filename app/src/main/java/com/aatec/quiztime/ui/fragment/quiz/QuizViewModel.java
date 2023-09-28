package com.aatec.quiztime.ui.fragment.quiz;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.aatec.quiztime.data.retrofit.QuizRepository;
import com.aatec.quiztime.data.retrofit.model.QuizModel;
import com.aatec.quiztime.ui.fragment.start_quiz.StartQuizFragment;

import java.util.function.Consumer;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class QuizViewModel extends ViewModel {

    private final QuizRepository quizRepository;

    private final SavedStateHandle state;

    private int points = 0;


    @Inject
    public QuizViewModel(QuizRepository quizRepository, SavedStateHandle state) {
        this.quizRepository = quizRepository;
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

    public int getPoints() {
        return points;
    }

    public void incrementPoints(Void ignoredUnused) {
        points++;
    }

}
