package com.example.viewpager.viewpager_sample.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.viewpager.R;

public class PagerIndicator extends LinearLayout {

    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDefaultMargin(int defaultMargin) {
        this.defaultMargin = defaultMargin;
    }

    public void setDefaultDuration(int defaultDuration) {
        this.defaultDuration = defaultDuration;
    }

    public void setDefaultScale(float defaultScale) {
        this.defaultScale = defaultScale;
    }

    public void setSelectScale(float selectScale) {
        this.selectScale = selectScale;
    }

    public void setDefaultImage(int defaultImage) {
        this.defaultImage = defaultImage;
    }

    public void setSelectedImage(int selectedImage) {
        this.selectedImage = selectedImage;
    }

    public void setImageDots(ImageView[] imageDots) {
        this.imageDots = imageDots;
    }

    private int defaultMargin = 10;

    private int defaultDuration = 300;

    private int dotSize = 15;

    private float defaultScale = 1f;

    private float selectScale = 1.2f;

    private int defaultImage = R.drawable.dot_default;
    private int selectedImage = R.drawable.dot_selected;

    private ImageView[] imageDots;

    private void setDotSize(int size) {
        dotSize = size;
        if (imageDots != null) {
            for (ImageView view : imageDots) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                        (dotSize, dotSize);
                params.topMargin = defaultMargin;
                params.bottomMargin = defaultMargin;
                params.leftMargin = defaultMargin;
                params.rightMargin = defaultMargin;
                params.gravity = Gravity.CENTER;

                view.setLayoutParams(params);
            }
        }
    }

    public PagerIndicator(Context context) {
        super(context);
        initialize(context);
    }

    public PagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    private void initialize(Context context) {
        initialize(context, null);
    }

    private void initialize(Context context, AttributeSet attrs) {
        this.context = context;
        readAttributes(attrs);
    }

    private void readAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PagerIndicator);
            defaultDuration = a.getInt(R.styleable.PagerIndicator_duration, defaultDuration);
            defaultMargin = a.getInt(R.styleable.PagerIndicator_marginBetweenDot, defaultMargin);

            defaultImage = a.getResourceId(R.styleable.PagerIndicator_defaultImage, defaultImage);
            selectedImage = a.getResourceId(R.styleable.PagerIndicator_selectImage, selectedImage);

            defaultScale = a.getFloat(R.styleable.PagerIndicator_defaultScale, defaultScale);
            selectScale = a.getFloat(R.styleable.PagerIndicator_selectScale, selectScale);
            a.recycle();
        }
    }

    /**
     * 선택된 점의 애니메이션
     *
     * @param view
     * @param startScale
     * @param endScale
     */
    private void selectScaleAnim(View view, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale,
                startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(defaultDuration);
        view.startAnimation(anim);
        view.setTag(view.getId(), true);
    }


    /**
     * 선택되지 않은 점의 애니메이션
     *
     * @param view
     * @param startScale
     * @param endScale
     */
    private void defaultScaleAnim(View view, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale,
                startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(defaultDuration);
        view.startAnimation(anim);
        view.setTag(view.getId(), false);
    }




    //    public methods
    public void createDots(int count, int selectedImage, int defaultImage) {
        this.selectedImage = selectedImage;
        this.defaultImage = defaultImage;
        createDots(count);
    }

    public void createDots(int count) {
        imageDots = new ImageView[count];
        for (int i = 0; i < count; i++) {
            imageDots[i] = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (dotSize, dotSize);
            params.topMargin = defaultMargin;
            params.bottomMargin = defaultMargin;
            params.leftMargin = defaultMargin;
            params.rightMargin = defaultMargin;
            params.gravity = Gravity.CENTER;

            imageDots[i].setLayoutParams(params);
            imageDots[i].setImageResource(defaultImage);
            imageDots[i].setTag(imageDots[i].getId(), false);
            this.addView(imageDots[i]);
        }
        selectDot(0);
    }

    public void selectDot(int pos) {
        for (int i = 0; i < imageDots.length; i++) {
            if (i == pos) {
                imageDots[i].setImageResource(selectedImage);
                selectScaleAnim(imageDots[i], defaultScale, selectScale);
            } else {
                if ((boolean) imageDots[i].getTag(imageDots[i].getId()) == true) {
                    imageDots[i].setImageResource(defaultImage);
                    defaultScaleAnim(imageDots[i], selectScale, defaultScale);
                }
            }
        }
    }
}
