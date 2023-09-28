package com.aatec.quiztime.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aatec.quiztime.data.room.model.QuizRoomModel;

import java.util.List;

@Dao
public interface QuizDao {
    @Query("SELECT * FROM quiz_table order by createdAt desc")
    LiveData<List<QuizRoomModel>> getQuiz();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuiz(QuizRoomModel model);

    @Delete
    void deleteQuiz(QuizRoomModel model);


    @Query("Delete from quiz_table ")
    void deleteAll();

}
