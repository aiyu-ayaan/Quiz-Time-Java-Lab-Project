package com.aatec.quiztime.ui.fragment.quiz;

import static com.aatec.quiztime.utils.Utils.category_list;
import static com.aatec.quiztime.utils.Utils.findNavController;
import static com.aatec.quiztime.utils.Utils.toast;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aatec.quiztime.R;
import com.aatec.quiztime.data.retrofit.model.QuizModel;
import com.aatec.quiztime.data.room.model.QuizRoomModel;
import com.aatec.quiztime.databinding.FragmentQuizBinding;
import com.aatec.quiztime.ui.fragment.quiz.pager.QuizQuestionFragment;
import com.aatec.quiztime.ui.fragment.quiz.pager.ViewPager2Adapter;
import com.aatec.quiztime.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizFragment extends BaseFragment {
    private static final String TAG = "QuizFragment";

    public QuizFragment() {
        super(R.layout.fragment_quiz);
    }

    private List<QuizModel.QuizData> quizList = new ArrayList<>();
    private final List<QuizRoomModel> quizRoomModels = new ArrayList<>();


    private FragmentQuizBinding binding;

    private QuizViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentQuizBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        setTopView();
        if (viewModel.getQuizDetails().isForNewQuiz())
            loadData();
        else
            loadForResult();
    }

    private void loadForResult() {
        binding.textViewQuestion.setText(getString(R.string.result));
        var data = viewModel.getQuizDetails();
        binding.progressIndicatorLoading.setVisibility(View.GONE);
        var fragmentList = getFragmentForResult(data.getQuizModel().getQuizModels());
        var adapter = new ViewPager2Adapter(getChildFragmentManager(), getLifecycle(), fragmentList);
        binding.pager.setVisibility(View.VISIBLE);
        binding.pager.setAdapter(adapter);
    }

    private List<Fragment> getFragmentForResult(List<QuizModel.QuizData> detailModels) {
        return detailModels.stream().map(v -> {
            var fragment = new QuizQuestionFragment();
            fragment.setQuizDataAndQuestionNumber(v, detailModels.indexOf(v) + 1);
            fragment.setShowingCorrectAnswer(true);
            fragment.setNavigateToNextAnswerListener(this::navigateToHome);
            return fragment;
        }).collect(Collectors.toList());
    }

    private void navigateToHome(Void ignoredUnused) {
        var action = QuizFragmentDirections.actionQuizFragmentToHomeFragment();
        findNavController(this).navigate(action);
    }


    private void loadData() {
        viewModel.loadQuizFromApi(this::onSuccess, this::onError);
    }

    private void onSuccess(QuizModel quizModel) {
        binding.progressIndicatorLoading.setVisibility(View.GONE);
        quizList = quizModel.getQuizModels();
        var fragmentList = getFragment(quizList);
        var adapter = new ViewPager2Adapter(getChildFragmentManager(), getLifecycle(), fragmentList);
        binding.pager.setVisibility(View.VISIBLE);
        binding.pager.setAdapter(adapter);
        binding.pager.setUserInputEnabled(false);
    }

    private List<Fragment> getFragment(List<QuizModel.QuizData> quizData) {
        return quizData.stream().map(v -> {
            var fragment = new QuizQuestionFragment();
            fragment.setQuizDataAndQuestionNumber(v, quizData.indexOf(v) + 1);
            fragment.setCorrectListener(c -> updateValue(quizData.indexOf(v)));
            fragment.setCheckAnswerListener(this::getPointsAndNavigate);
            fragment.setUserAnswerListener(v::setUserAnswer);
            return fragment;
        }).collect(Collectors.toList());
    }

    private void updateValue(int pos) {
        quizList.get(pos).setAnsweredCorrectly(true);
        viewModel.incrementPoints();
    }

    private void getPointsAndNavigate(Void ignoredUnused) {
        viewModel.insertData(quizList, error -> {
            if (error == null) {
                var action = QuizFragmentDirections.actionQuizFragmentToScoreBoard(viewModel.getPoints());
                findNavController(this).navigate(action);
            } else {
                toast(requireContext(), error.getMessage());
                Log.d(TAG, "getPointsAndNavigate: " + error.getMessage());
            }
        });
    }

    private void onError(String m) {
        toast(requireContext(), m);
    }

    private void setTopView() {
        binding.textViewQuestion.setText(getResources().getString(R.string.question_description, getCategoryFromId(viewModel.getQuizDetails().getCategory())));
        binding.progressIndicatorLoading.setVisibility(View.VISIBLE);
    }

    private String getCategoryFromId(int id) {
        return category_list.stream().filter(v -> v.id == id).findFirst().get().name;
    }
}
