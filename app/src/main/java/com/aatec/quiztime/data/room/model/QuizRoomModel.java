package com.aatec.quiztime.data.room.model;

import android.util.Pair;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Keep
@Entity(tableName = "quiz_table")
public class QuizRoomModel implements Serializable {


    private String category;
    private String difficulty;
    private List<Questions> questions;
    @PrimaryKey()
    private long createdAt;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public QuizRoomModel(String category, String difficulty, List<Questions> questions) {
        this.category = category;
        this.difficulty = difficulty;
        this.questions = questions;
        this.createdAt = System.currentTimeMillis();
    }

    public QuizRoomModel() {
        this.createdAt = System.currentTimeMillis();
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    @Ignore
    public Pair<Integer, Integer> getScore() {
        int correct = 0;
        for (Questions question : questions) {
            if (question.isAnsweredCorrectly())
                correct++;

        }
        return new Pair<>(correct, questions.size());
    }

    //     Inner class part
    @Keep
    public static class Questions implements Serializable {

        private final String question;
        private final String correctAnswer;
        private final String[] incorrectAnswers;

        private final boolean isAnsweredCorrectly;

        private final String userAnswer;

        public Questions(String question, String correctAnswer, String[] incorrectAnswers, boolean isAnsweredCorrectly, String userAnswer) {
            this.question = question;
            this.correctAnswer = correctAnswer;
            this.incorrectAnswers = incorrectAnswers;
            this.isAnsweredCorrectly = isAnsweredCorrectly;
            this.userAnswer = userAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public String[] getIncorrectAnswers() {
            return incorrectAnswers;
        }

        public boolean isAnsweredCorrectly() {
            return isAnsweredCorrectly;
        }

        public String getUserAnswer() {
            return userAnswer;
        }



        @NonNull
        @Override
        public String toString() {
            return "Questions{" +
                    "question='" + question + '\'' +
                    ", correctAnswer='" + correctAnswer + '\'' +
                    ", incorrectAnswers=" + Arrays.toString(incorrectAnswers) +
                    ", isCorrect=" + isAnsweredCorrectly +
                    '}';
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "QuizRoomModel{" +
                "category='" + category + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", questions=" + questions +
                ", createdAt=" + createdAt +
                '}';
    }
}
