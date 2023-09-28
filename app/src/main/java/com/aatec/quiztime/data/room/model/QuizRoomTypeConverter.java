package com.aatec.quiztime.data.room.model;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class QuizRoomTypeConverter {

    @TypeConverter
    public static List<QuizRoomModel.Questions> fromString(String value) {
        var gson = new Gson();
        Type listType = new TypeToken<List<QuizRoomModel.Questions>>() {
        }.getType();
        return gson.fromJson(value, listType);
    }


    @TypeConverter
    public static String toString(List<QuizRoomModel.Questions> list) {
        var gson = new Gson();
        return gson.toJson(list);
    }
}
