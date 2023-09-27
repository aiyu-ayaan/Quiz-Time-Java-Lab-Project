package com.aatec.quiztime.ui.fragment.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aatec.quiztime.R;
import com.aatec.quiztime.data.retrofit.QuizRepository;
import com.aatec.quiztime.databinding.FragmentHomeBinding;
import com.aatec.quiztime.utils.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    private FragmentHomeBinding binding;

    @Inject
    QuizRepository quizRepository;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);

    }
}
