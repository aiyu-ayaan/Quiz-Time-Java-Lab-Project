package com.aatec.quiztime.ui.fragment.quiz.pager;

import static com.aatec.quiztime.utils.Utils.toast;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.aatec.quiztime.R;
import com.aatec.quiztime.data.retrofit.model.QuizModel;
import com.aatec.quiztime.databinding.FragmentQuestionQuizBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class QuizQuestionFragment extends Fragment {
    private static final String TAG = "QuizQuestionFragment";

    public QuizQuestionFragment() {
        super(R.layout.fragment_question_quiz);
    }

    private FragmentQuestionQuizBinding binding;

    private int questionNumber = 0;
    private QuizModel.QuizData quizData = null;

    private String answer = "";

    private Consumer<Void> checkAnswerListener = null;

    private Consumer<Void> correctListener = null;


    public void setCheckAnswerListener(Consumer<Void> checkAnswerListener) {
        this.checkAnswerListener = checkAnswerListener;
    }

    public void setCorrectListener(Consumer<Void> correctListener) {
        this.correctListener = correctListener;
    }


    public void setQuizDataAndQuestionNumber(QuizModel.QuizData quizData, int questionNumber) {
        this.quizData = quizData;
        this.questionNumber = questionNumber;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentQuestionQuizBinding.bind(view);
        bindView();
    }

    private void bindView() {
        if (quizData == null) {
            toast(requireContext(), "Quiz data is null");
            return;
        }
        Log.d(TAG, "bindView: " + quizData.getCorrectAnswer());
        binding.textViewQuestion.setText(
                requireContext().getResources()
                        .getString(
                                R.string.sno_question,
                                String.valueOf(questionNumber),
                                Html.fromHtml(quizData.getQuestion(), Html.FROM_HTML_MODE_LEGACY)
                        )
        );
        var questions = new ArrayList<String>() {{
            add(quizData.getCorrectAnswer());
            this.addAll(Arrays.stream(quizData.getIncorrectAnswers()).collect(Collectors.toList()));
        }};
        java.util.Collections.shuffle(questions);
        binding.radioButtonAnswer1.setText(Html.fromHtml(questions.get(0), Html.FROM_HTML_MODE_LEGACY));
        binding.radioButtonAnswer2.setText(Html.fromHtml(questions.get(1), Html.FROM_HTML_MODE_LEGACY));
        binding.radioButtonAnswer3.setText(Html.fromHtml(questions.get(2), Html.FROM_HTML_MODE_LEGACY));
        binding.radioButtonAnswer4.setText(Html.fromHtml(questions.get(3), Html.FROM_HTML_MODE_LEGACY));
        setAnswer();
        var viewPager = (ViewPager2) requireParentFragment().requireView().findViewById(R.id.pager);
        binding.buttonNext.setText(
                viewPager.getCurrentItem() == 9 ? "Submit" : "Next"
        );
        binding.buttonNext.setOnClickListener(v -> {
            checkAnswer();
            if (viewPager.getCurrentItem() == 9) {
                if (checkAnswerListener == null) {
                    toast(requireContext(), "Check answer listener is null");
                    return;
                }
                checkAnswerListener.accept(null);
            } else
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        });
    }

    private void checkAnswer() {
        if (answer.equals(quizData.getCorrectAnswer())) {
            if (correctListener == null) {
                toast(requireContext(), "Correct listener is null");
                return;
            }
            correctListener.accept(null);
        }
    }

    private void setAnswer() {
        binding.radioButtonAnswer1.setOnClickListener(v -> answer = binding.radioButtonAnswer1.getText().toString());
        binding.radioButtonAnswer2.setOnClickListener(v -> answer = binding.radioButtonAnswer2.getText().toString());
        binding.radioButtonAnswer3.setOnClickListener(v -> answer = binding.radioButtonAnswer3.getText().toString());
        binding.radioButtonAnswer4.setOnClickListener(v -> answer = binding.radioButtonAnswer4.getText().toString());
    }
}
