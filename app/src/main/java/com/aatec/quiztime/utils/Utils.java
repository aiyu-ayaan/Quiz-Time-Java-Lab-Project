package com.aatec.quiztime.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.function.Consumer;

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
        var view = checkNotNull(fragment.getView(), e -> toast(fragment.getContext(), e.getMessage()));
        return Navigation.findNavController(view);
    }
}
