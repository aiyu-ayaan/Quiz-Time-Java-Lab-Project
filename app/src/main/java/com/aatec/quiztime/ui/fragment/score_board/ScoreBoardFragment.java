package com.aatec.quiztime.ui.fragment.score_board;

import static com.aatec.quiztime.utils.Utils.findNavController;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aatec.quiztime.R;
import com.aatec.quiztime.databinding.FragmentFinalScoreBinding;
import com.aatec.quiztime.utils.BaseFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ScoreBoardFragment extends BaseFragment {
    public ScoreBoardFragment() {
        super(R.layout.fragment_final_score);
    }

    private FragmentFinalScoreBinding binding;

    private ScoreBoardFragmentArgs args;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFinalScoreBinding.bind(view);
        args = ScoreBoardFragmentArgs.fromBundle(getArguments());
        binding.textViewCongratulations.setText(getString(R.string.congratulations, "Ayaan"));
        binding.textViewScore.setText(getString(R.string.score, String.valueOf(args.getPoints())));
        var progress = (int) ((args.getPoints() / 10.0) * 100);
        binding.progressIndicatorPercentage.setProgress(progress, true);
        binding.buttonPlayAgain.setOnClickListener(v -> findNavController(this)
                .navigate(ScoreBoardFragmentDirections.actionScoreBoardToHomeFragment())
        );
    }
}
