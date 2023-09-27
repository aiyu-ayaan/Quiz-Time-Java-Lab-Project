package com.aatec.quiztime.data.retrofit;

import android.util.Log;

import androidx.annotation.NonNull;

import com.aatec.quiztime.data.retrofit.model.QuizModel;

import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {
    private static final String TAG = "QuizRepository";
    private final QuizApi quizApi;

    @Inject
    public QuizRepository(QuizApi quizApi) {
        this.quizApi = quizApi;
    }

    public void getQuiz(
            String difficulty,
            String category,
            Consumer<QuizModel> onSuccess,
            Consumer<String> onError
    ) {
        quizApi.getQuiz(difficulty, category).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<QuizModel> call, @NonNull Response<QuizModel> response) {
                Log.d(TAG, "onResponse: " + call.request().url());
                if (response.isSuccessful())
                    onSuccess.accept(response.body());
                else
                    onError.accept(response.message());

            }

            @Override
            public void onFailure(@NonNull Call<QuizModel> call, @NonNull Throwable t) {
                Log.d(TAG, "onResponse: " + call.request().url());
                onError.accept(t.getMessage());
            }
        });
    }
}
