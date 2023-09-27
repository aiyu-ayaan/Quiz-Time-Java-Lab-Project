package com.aatec.quiztime.ui.fragment.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.aatec.quiztime.R;
import com.aatec.quiztime.databinding.FragmentHomeBinding;
import com.aatec.quiztime.utils.BaseFragment;

public class HomeFragment extends BaseFragment {

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    private FragmentHomeBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);

    }
}
