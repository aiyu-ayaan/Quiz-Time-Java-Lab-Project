package com.aatec.quiztime.ui.fragment.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aatec.quiztime.data.room.QuizRoomRepository;
import com.aatec.quiztime.data.room.model.QuizRoomModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final QuizRoomRepository repository;

    @Inject
    public HomeViewModel(QuizRoomRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<QuizRoomModel>> getQuiz() {
        return repository.getQuiz();
    }
}
