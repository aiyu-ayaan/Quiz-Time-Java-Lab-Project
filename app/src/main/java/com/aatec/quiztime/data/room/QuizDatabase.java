package com.aatec.quiztime.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.aatec.quiztime.data.room.model.QuizRoomModel;
import com.aatec.quiztime.data.room.model.QuizRoomTypeConverter;

@Database(entities = {QuizRoomModel.class}, version = 1, exportSchema = false)
@TypeConverters({QuizRoomTypeConverter.class})
public abstract class QuizDatabase extends RoomDatabase {

    public abstract QuizDao quizDao();

    public static String DATABASE_NAME = "quiz_database";
}
