package com.aatec.quiztime.ui.fragment.start_quiz;

import static com.aatec.quiztime.utils.Utils.category_list;
import static com.aatec.quiztime.utils.Utils.checkNotNull;
import static com.aatec.quiztime.utils.Utils.findNavController;
import static com.aatec.quiztime.utils.Utils.toast;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aatec.quiztime.R;
import com.aatec.quiztime.databinding.FragmentStartQuizBinding;
import com.aatec.quiztime.utils.BaseFragment;
import com.aatec.quiztime.utils.Utils;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class StartQuizFragment extends BaseFragment {
    public StartQuizFragment() {
        super(R.layout.fragment_start_quiz);
    }

    private FragmentStartQuizBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentStartQuizBinding.bind(view);
        setUpExposerDropDownCategory();
        setUpExposerDropDownDifficulty();
        binding.buttonEnter.setOnClickListener(this::onNewQuizClick);
    }

    private void setUpExposerDropDownCategory() {
        var items = category_list.stream().map(v -> v.name).toArray(String[]::new);
        checkNotNull(binding.outlinedTextFieldCategory.getEditText(),
                e -> toast(requireContext(), e.getMessage()),
                editText -> ((MaterialAutoCompleteTextView) editText).setSimpleItems(items)
        );
    }

    private void setUpExposerDropDownDifficulty() {
        var items = Arrays.stream(Utils.Difficulty.values()).map(Enum::name).toArray(String[]::new);
        checkNotNull(binding.outlinedTextFieldDifficulty.getEditText(),
                e -> toast(requireContext(), e.getMessage()),
                editText -> ((MaterialAutoCompleteTextView) editText).setSimpleItems(items)
        );
    }

    private void onNewQuizClick(View view) {
        var category = Objects.requireNonNull(binding.outlinedTextFieldCategory.getEditText()).getText().toString();
        if (category.isEmpty()) {
            binding.outlinedTextFieldCategory.setError("Please select category");
            return;
        }
        binding.outlinedTextFieldCategory.setError(null);
        var difficulty = Objects.requireNonNull(binding.outlinedTextFieldDifficulty.getEditText()).getText().toString().toLowerCase();
        if (difficulty.isEmpty()) {
            binding.outlinedTextFieldDifficulty.setError("Please select difficulty");
            return;
        }
        binding.outlinedTextFieldDifficulty.setError(null);

        var category_id = findCategory(category);
        navigateToQuizFragment(new QuizDetailModel(category_id, difficulty));

    }

    private void navigateToQuizFragment(QuizDetailModel quizDetailModel) {
        var action = StartQuizFragmentDirections.actionStartQuizFragmentToQuizFragment(quizDetailModel);
        findNavController(this).navigate(action);
    }

    private int findCategory(String category) {
        return category_list.stream().filter(v -> v.name.equals(category)).findFirst().get().id;
    }

    @Keep
    public static class QuizDetailModel implements Serializable {
        private final int category;
        private final String difficulty;

        public QuizDetailModel(int category, String difficulty) {
            this.category = category;
            this.difficulty = difficulty;
        }

        public int getCategory() {
            return category;
        }

        public String getDifficulty() {
            return difficulty;
        }
    }
}
