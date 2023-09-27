package com.aatec.quiztime.data.retrofit;

import com.aatec.quiztime.data.retrofit.model.QuizModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApi {

    String BASE_URL = "https://opentdb.com/";


    @GET("api.php?amount=10&type=multiple")
    Call<QuizModel> getQuiz(
            @Query("difficulty") String difficulty,
            @Query("category") String category
    );
}
