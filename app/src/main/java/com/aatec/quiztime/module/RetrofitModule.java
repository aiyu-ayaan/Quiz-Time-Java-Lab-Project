package com.aatec.quiztime.module;

import androidx.annotation.NonNull;

import com.aatec.quiztime.data.retrofit.QuizApi;

import org.jetbrains.annotations.Contract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@InstallIn(SingletonComponent.class)
@Module
public class RetrofitModule {


    @NonNull
    @Contract(" -> new")
    @Provides
    @Singleton
    public static GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @NonNull
    @Contract("_ -> new")
    @Provides
    @Singleton
    public static Retrofit provideRetrofit(
            GsonConverterFactory gsonConverterFactory
    ) {
        return new Retrofit.Builder()
                .baseUrl(QuizApi.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @NonNull
    @Provides
    @Singleton
    public static QuizApi provideQuizApi(@NonNull Retrofit retrofit) {
        return retrofit.create(QuizApi.class);
    }
}
