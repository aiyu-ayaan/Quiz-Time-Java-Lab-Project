package com.aatec.quiztime.ui.fragment.home;

import static com.aatec.quiztime.utils.Utils.findNavController;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

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
    private HomeViewModel viewModel;
    private RecyclerViewAdapter adapter;

    @Inject
    QuizRepository quizRepository;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.buttonEnter.setOnClickListener(this::onNewQuizClick);
        setRecyclerView();
        observeData();
    }

    private void setRecyclerView() {
        adapter = new RecyclerViewAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void observeData() {
        viewModel.getQuiz().observe(getViewLifecycleOwner(), quiz -> {
            binding.imageViewEmpty.setVisibility(quiz.isEmpty() ? View.VISIBLE : View.GONE);
            binding.textViewEmpty.setVisibility(quiz.isEmpty() ? View.VISIBLE : View.GONE);
            adapter.submitList(quiz);
        });
    }

    private void onNewQuizClick(View view) {
        var action = HomeFragmentDirections.actionHomeFragmentToStartQuizFragment();
        findNavController(this).navigate(action);
    }
}
