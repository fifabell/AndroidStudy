package com.example.test01;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

public class HorizontalProgress extends FrameLayout {
    private View one;
    private View two;

    private int animationDuration;
    private int startColor;
    private int endColor;

    private int laidOutWidth;

    ValueAnimator animator;

    private boolean isStart;

    public HorizontalProgress(Context context) {
        super(context);
        inflate(context, R.layout.view_horizontal_progress, this);
        init();
        isStart = false;

        setColors();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                laidOutWidth = getWidth();
                animator = ValueAnimator.ofInt(0, 2 * laidOutWidth);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                if (isStart) {
                    startProgress();
                }
            }
        });
    }

    public HorizontalProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_horizontal_progress, this);
        init();

        setColors();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                laidOutWidth = getWidth();
                animator = ValueAnimator.ofInt(0, 2 * laidOutWidth);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                if (isStart) {
                    startProgress();
                }
            }
        });

        isStart = false;
    }

    private void setColors() {
        GradientDrawable mColor = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{startColor, endColor});
        GradientDrawable mColor2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{endColor, startColor});

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            one.setBackground(mColor);
            two.setBackground(mColor2);
        } else {
            one.setBackgroundDrawable(mColor);
            two.setBackgroundDrawable(mColor2);
        }


        Log.d("COLOR_CHECK", "[setColors] start : "+startColor + " end : "+endColor);

//        ViewCompat.setBackground(one, mColor);
//        ViewCompat.setBackground(two, mColor2);
    }


    /**
     * private methods
     */
    private void init() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            startColor = getContext().getColor(R.color.colorGray);
            endColor = getContext().getColor(R.color.colorWhiteGray);
        } else {
            startColor = getContext().getResources().getColor(R.color.colorGray);
            endColor = getContext().getResources().getColor(R.color.colorWhiteGray);
        }
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        animationDuration = 2000;
        setBackgroundResource(R.drawable.backgorund_round_rectagle_layout);
    }

    private ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int offset = (int) valueAnimator.getAnimatedValue();
            one.setTranslationX(calculateOneTranslationX(laidOutWidth, offset));
            two.setTranslationX(calculateTwoTranslationX(laidOutWidth, offset));

        }
    };

    private int calculateOneTranslationX(int width, int offset) {
        return (-1 * width) + offset;
    }

    private int calculateTwoTranslationX(int width, int offset) {
        if (offset <= width) {
            return offset;
        } else {
            return (-2 * width) + offset;
        }
    }


    /**
     * public methods
     */
    public void startProgress() {
        isStart = true;
        if (animator != null) {
            animator.setInterpolator(new LinearInterpolator());
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.setDuration(animationDuration);
            animator.addUpdateListener(updateListener);
            animator.start();
        }
    }

    public void stopProgress() {
        isStart = false;
        try {
            animator.cancel();
            animator.end();
            animator.cancel();
        } catch (Exception e) {
        }
    }

    public void setStartColor(int color) {
        Log.d("COLOR_CHECK", "[setStartColor] color : "+ color );
        startColor = color;
        setColors();
    }

    public void setEndColor(int color) {
        Log.d("COLOR_CHECK", "[setEndColor] color : "+ color);
        endColor = color;
        setColors();
    }


    public void setDuration(int duration) {
        animationDuration = duration;
    }


}