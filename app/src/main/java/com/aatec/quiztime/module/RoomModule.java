package com.aatec.quiztime.module;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.aatec.quiztime.data.room.QuizDao;
import com.aatec.quiztime.data.room.QuizDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class RoomModule {


    @NonNull
    @Provides
    @Singleton
    public static QuizDatabase provideDatabase(
            @ApplicationContext Context context
    ) {
        return Room.databaseBuilder(
                context,
                QuizDatabase.class,
                QuizDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build();
    }

    @Provides
    @Singleton
    public static QuizDao provideQuizDao(
            @NonNull QuizDatabase quizDatabase
    ) {
        return quizDatabase.quizDao();
    }
}
