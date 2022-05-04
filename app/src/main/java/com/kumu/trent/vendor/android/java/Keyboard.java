package com.kumu.trent.vendor.android.java;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;



public class Keyboard {
    public static void hideKeyboard(Activity activity) {
        InputMethodManager keyboard  = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        keyboard .hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboard(Context context) {
        Activity activity = (Activity) context;
        InputMethodManager keyboard  = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        keyboard .hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(Activity activity, EditText editText) {
        editText.requestFocus();
        InputMethodManager keyboard  = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        keyboard.showSoftInput(editText, 0);
    }

    public static void showForceKeyboard(Activity activity, EditText editText) {
        editText.requestFocus();
        InputMethodManager keyboard  = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void hideForceKeyboard(Activity activity, View view) {
        view.requestFocus();
        InputMethodManager keyboard  = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
