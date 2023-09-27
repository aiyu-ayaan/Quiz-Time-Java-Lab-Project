package com.aatec.quiztime.ui.main_activity;


import static com.aatec.quiztime.utils.Utils.isNotNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.aatec.quiztime.R;
import com.aatec.quiztime.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        var binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return isNotNull(navController.navigateUp()) || super.onSupportNavigateUp();
    }
}