package com.aatec.quiztime.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import com.google.android.material.transition.MaterialSharedAxis;

public class Utils {

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static <T> T checkNotNull(T reference, Consumer<Exception> exception) {
        if (reference == null) {
            exception.accept(new NullPointerException());
        }
        return reference;
    }

    public static <T> void checkNotNull(T reference, Consumer<Exception> exception, Consumer<T> onSuccess) {
        if (reference == null) {
            exception.accept(new NullPointerException());
        }
        onSuccess.accept(reference);
    }

    public static <T> Boolean isNotNull(T reference) {
        return reference != null;
    }

    public static <T extends Fragment> NavController findNavController(T fragment) {
        fragment.setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true));
        fragment.setReenterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false));
        var view = checkNotNull(fragment.getView(), e -> toast(fragment.getContext(), e.getMessage()));
        return Navigation.findNavController(view);
    }

    public enum Difficulty {
       Easy, Medium, Hard
    }

    public static class Category {
        public final int id;
        public final String name;

        public Category(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static final  List<Category> category_list = new ArrayList<>() {{
        add(new Category(0, "Any Category"));
        add(new Category(9, "General Knowledge"));
        add(new Category(10, "Entertainment: Books"));
        add(new Category(11, "Entertainment: Film"));
        add(new Category(12, "Entertainment: Music"));
        add(new Category(13, "Entertainment: Musicals & Theatres"));
        add(new Category(14, "Entertainment: Television"));
        add(new Category(15, "Entertainment: Video Games"));
        add(new Category(16, "Entertainment: Board Games"));
        add(new Category(17, "Science & Nature"));
        add(new Category(18, "Science: Computers"));
        add(new Category(19, "Science: Mathematics"));
        add(new Category(20, "Mythology"));
        add(new Category(21, "Sports"));
        add(new Category(22, "Geography"));
        add(new Category(23, "History"));
        add(new Category(24, "Politics"));
        add(new Category(25, "Art"));
        add(new Category(26, "Celebrities"));
        add(new Category(27, "Animals"));
        add(new Category(28, "Vehicles"));
        add(new Category(29, "Entertainment: Comics"));
        add(new Category(30, "Science: Gadgets"));
        add(new Category(31, "Entertainment: Japanese Anime & Manga"));
        add(new Category(32, "Entertainment: Cartoon & Animations"));
    }};

}
