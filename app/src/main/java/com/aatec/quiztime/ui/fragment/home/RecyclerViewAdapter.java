package com.aatec.quiztime.ui.fragment.home;

import static com.aatec.quiztime.utils.Utils.convertToData;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aatec.quiztime.data.room.model.QuizRoomModel;
import com.aatec.quiztime.databinding.RowQuizHomeBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<QuizRoomModel> list = List.of();

    @SuppressLint("NotifyDataSetChanged")
    public void submitList(List<QuizRoomModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowQuizHomeBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final RowQuizHomeBinding binding;

        public ViewHolder(RowQuizHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(QuizRoomModel model) {
            binding.tvQuizTitle.setText(String.format("%s - %s", model.getCategory(), model.getDifficulty()));
            binding.tvScore.setText(String.format("Score - %s/%s", model.getScore().first, model.getScore().second) +
                    "  "
                    + convertToData(model.getCreatedAt()));
            binding.progressIndicatorPercentage.setProgress(model.getScore().first * 100 / model.getScore().second,true);
        }
    }
}
