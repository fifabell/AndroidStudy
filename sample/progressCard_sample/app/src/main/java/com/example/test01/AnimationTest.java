package com.example.test01;

import android.os.Build;
import android.view.View;

public class AnimationTest {

    public static void showWithScale(View v, int duration) {
        v.setVisibility(View.VISIBLE);
        v.animate().setDuration(duration).scaleX(1f).scaleY(1f).start();
    }

    public static void hideWithAlpha(final View v, int duration, final int visibility) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.animate().setDuration(duration).alpha(0f).withEndAction(new Runnable() {
                @Override
                public void run() {
                    v.setVisibility(visibility);
                }
            }).start();
        }
    }

    public static void showWithAlpha(View v, int duration) {
        v.setVisibility(View.VISIBLE);
        v.animate().setDuration(duration).alpha(1f).start();
    }

    public static void showWithAlphaSlideUp(View v, int duration) {
        v.animate().setDuration(0).alpha(0f).translationY(50).start();
        v.setVisibility(View.VISIBLE);
        v.animate().setDuration(duration).alpha(1f).translationY(0).start();
    }
}

