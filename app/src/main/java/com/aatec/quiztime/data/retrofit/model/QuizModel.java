package com.aatec.quiztime.data.retrofit.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Keep
public class QuizModel implements Serializable {

    @SerializedName("results")
    private List<QuizData> quizModels;

    public QuizModel(List<QuizData> quizModels) {
        this.quizModels = quizModels;
    }


    public List<QuizData> getQuizModels() {
        return quizModels;
    }

    public void setQuizModels(List<QuizData> quizModels) {
        this.quizModels = quizModels;
    }

    public QuizModel() {
    }

    public static class QuizData {
        private String category;
        private String type;
        private String difficulty;
        private String question;
        @SerializedName("correct_answer")
        private String correctAnswer;

        @SerializedName("incorrect_answers")
        private String[] incorrectAnswers;

        @Expose(serialize = false, deserialize = false)
        private boolean isAnsweredCorrectly = false;

        @Expose(serialize = false, deserialize = false)
        private String userAnswer = "";


        public QuizData(String category, String type, String difficulty, String question, String correctAnswer, String[] incorrectAnswers) {
            this.category = category;
            this.type = type;
            this.difficulty = difficulty;
            this.question = question;
            this.correctAnswer = correctAnswer;
            this.incorrectAnswers = incorrectAnswers;
            this.isAnsweredCorrectly = false;
            this.userAnswer = "";
        }


        public QuizData(String category, String type, String difficulty, String question, String correctAnswer, String[] incorrectAnswers, boolean isAnsweredCorrectly, String userAnswer) {
            this.category = category;
            this.type = type;
            this.difficulty = difficulty;
            this.question = question;
            this.correctAnswer = correctAnswer;
            this.incorrectAnswers = incorrectAnswers;
            this.isAnsweredCorrectly = isAnsweredCorrectly;
            this.userAnswer = userAnswer;
        }

        public QuizData() {
        }

        public String getCategory() {
            return category;
        }

        public String getType() {
            return type;
        }

        public String getDifficulty() {
            return difficulty;
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

        public void setAnsweredCorrectly(boolean answeredCorrectly) {
            isAnsweredCorrectly = answeredCorrectly;
        }

        public String getUserAnswer() {
            return userAnswer;
        }

        public void setUserAnswer(String userAnswer) {
            this.userAnswer = userAnswer;
        }

        @NonNull
        @Override
        public String toString() {
            return "QuizData{" +
                    "category='" + category + '\'' +
                    ", type='" + type + '\'' +
                    ", difficulty='" + difficulty + '\'' +
                    ", question='" + question + '\'' +
                    ", correctAnswer='" + correctAnswer + '\'' +
                    ", incorrectAnswers=" + Arrays.toString(incorrectAnswers) +
                    ", isAnsweredCorrectly=" + isAnsweredCorrectly +
                    ", userAnswer='" + userAnswer + '\'' +
                    '}';
        }
    }
}
