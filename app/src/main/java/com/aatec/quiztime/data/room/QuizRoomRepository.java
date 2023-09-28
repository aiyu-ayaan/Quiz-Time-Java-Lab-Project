package com.aatec.quiztime.data.room;

import androidx.lifecycle.LiveData;

import com.aatec.quiztime.data.room.model.QuizRoomModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class QuizRoomRepository {
    private final QuizDao dao;

    private final Executor executor = Executors.newSingleThreadExecutor();

    @Inject
    public QuizRoomRepository(QuizDao dao) {
        this.dao = dao;
    }

    public void insertQuiz(QuizRoomModel model) {
        executor.execute(() -> dao.insertQuiz(model));
    }

    public void deleteQuiz(QuizRoomModel model) {
        executor.execute(() -> dao.deleteQuiz(model));
    }

    public void deleteAll() {
        executor.execute(dao::deleteAll);
    }

    public LiveData<List<QuizRoomModel>> getQuiz() {
        return dao.getQuiz();
    }

}
