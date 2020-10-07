package com.example.test01;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProgressCard extends FrameLayout {

    private String TAG = "ProgressCard";

    private int[] layouts = {R.layout.view_progress_card_type_1, R.layout.view_progress_card_type_2,
            R.layout.view_progress_card_type_3, R.layout.view_progress_card_type_4, R.layout.view_progress_card_type_5,
            R.layout.view_progress_card_type_6};

    private int mType = 0;

    int startColor;

    int endColor;

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    int duration = 1000;

    ArrayList<HorizontalProgress> mViews;

    public ProgressCard(Context context) {
        super(context);
        initializer(context);
    }

    public ProgressCard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializer(context, attrs);
    }

    public ProgressCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializer(context, attrs);
    }

    public ProgressCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializer(context, attrs);
    }

    /**
     * private methods
     */
    private void initializer(Context context) {
        inflate(context, layouts[mType], this);
        mViews = new ArrayList<>();
        searchProgress();
        setVisibility(GONE);
        setColorAttrs();
    }

    private void initializer(Context context, AttributeSet attrs) {
        readAttributes(attrs);
        createView(context);
        mViews = new ArrayList<>();
        searchProgress();
        setVisibility(GONE);
        setColorAttrs();
    }

    private void createView(Context context) {
        inflate(context, layouts[mType], this);
    }

    private void readAttributes(AttributeSet attrs) {
        int startColor = 0;
        int endColor = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            startColor = getContext().getColor(R.color.colorGray);
            endColor = getContext().getColor(R.color.colorWhiteGray);
        } else {
            startColor = getContext().getResources().getColor(R.color.colorGray);
            endColor = getContext().getResources().getColor(R.color.colorWhiteGray);
        }
        Log.d(TAG, " before read color id : " + this.startColor);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressCard);
        this.mType = a.getInt(R.styleable.ProgressCard_type, 0);
        this.startColor = a.getColor(R.styleable.ProgressCard_gradientStartColor, startColor);
        this.endColor = a.getColor(R.styleable.ProgressCard_gradientEndColor, endColor);
        this.duration = a.getInt(R.styleable.ProgressCard_animationDuration, 2000);
        Log.d(TAG, "after read color id : " + this.startColor);
        a.recycle();
    }

    private void searchProgress() {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            searchProgress(v);
        }
    }

    private void searchProgress(View v) {
        if (v instanceof HorizontalProgress) {
            HorizontalProgress progress = (HorizontalProgress) v;
            mViews.add(progress);
        } else if (v instanceof LinearLayout) {
            LinearLayout layout = (LinearLayout) v;
            int count = layout.getChildCount();
            for (int i = 0; i < count; i++) {
                searchProgress(layout.getChildAt(i));
            }
        }
    }

    private void setColorAttrs() {
        int count = mViews.size();
        for (int i = 0; i < count; i++) {
            HorizontalProgress progress = mViews.get(i);
            progress.setStartColor(startColor);
            progress.setEndColor(endColor);
            progress.setDuration(duration);
        }
    }

    /**
     * public methods
     */

    public void setType(int type) {
        if (type < 0 || type >= layouts.length) {
            return;
        }
        mType = type;
        createView(getContext());
    }


    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {
            setAlpha(1f);
            int count = mViews.size();
            for (int i = 0; i < count; i++) {
                HorizontalProgress progress = mViews.get(i);
                progress.setStartColor(startColor);
                progress.setEndColor(endColor);
                progress.setDuration(duration);
                progress.startProgress();
            }
        } else {
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                HorizontalProgress progress = mViews.get(i);
                progress.stopProgress();
            }
        }
    }
}
