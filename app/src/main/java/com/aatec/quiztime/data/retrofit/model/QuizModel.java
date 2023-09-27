package com.aatec.quiztime.data.retrofit.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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

        public QuizData(String category, String type, String difficulty, String question, String correctAnswer, String[] incorrectAnswers) {
            this.category = category;
            this.type = type;
            this.difficulty = difficulty;
            this.question = question;
            this.correctAnswer = correctAnswer;
            this.incorrectAnswers = incorrectAnswers;
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
    }
}
