package com.example.myapplication;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.makeramen.roundedimageview.RoundedImageView;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    private static final int TOUCH_CLICK_TIME = 250;

    private ValueAnimator valueAnimator;
    private RoundedImageView imageView;
    private PopupWindow popupWindow;
    private boolean isShowPopup;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(1000);
        imageView = new RoundedImageView(this);
        imageView.setImageResource(R.drawable.avatar);
        imageView.setOval(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setY(getStatusBarHeight() + getBubbleSpacing());
        imageView.setX(getBubbleSpacing());
        int avatarSize = (int) convertDpToPixel(72, this);
        ViewGroup.LayoutParams params = new ActionBar.LayoutParams(avatarSize,
                avatarSize);
//        getWindow().addContentView(imageView, params);
        ((ViewGroup) getWindow().getDecorView()).addView(imageView, params);


        imageView.setOnTouchListener(new View.OnTouchListener() {
            private float initialX;
            private float initialY;
            private float initialTouchX;
            private float initialTouchY;
            private int lastAction;
            private long touchStartTime;
            private long lastTouchTime;

            @Override
            public boolean onTouch(View view, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchStartTime = System.currentTimeMillis();
                        initialX = view.getX();
                        initialY = view.getY();
                        initialTouchX = e.getRawX();
                        initialTouchY = e.getRawY();
                        lastAction = e.getAction();
                        imageView.animate().cancel();
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        if (popupWindow != null && popupWindow.isShowing()) {
//                            popupWindow.dismiss();
//                        }
                        float itemX = initialX + (e.getRawX() - initialTouchX);
                        float itemY = initialY + (e.getRawY() - initialTouchY);
                        imageView.setX(itemX);
                        imageView.setY(itemY);
                        lastAction = e.getAction();
                        break;
                    case MotionEvent.ACTION_UP:
                        lastTouchTime = System.currentTimeMillis();
                        boolean hasTranslation = imageView.getX() != initialX || imageView.getY() != initialY;
                        if (lastTouchTime - touchStartTime < TOUCH_CLICK_TIME && !hasTranslation) {
                            onBubbleClick(imageView);
                        } else {
                            float upItemY = imageView.getY();
                            if (upItemY < getStatusBarHeight()) {
                                upItemY = getStatusBarHeight() + getBubbleSpacing();
                            } else if (upItemY + imageView.getHeight() > getScreenHeight()) {
                                upItemY = getScreenHeight() - imageView.getHeight() - getBubbleSpacing();
                            }
                            if ((imageView.getX() + imageView.getWidth() / 2f) < getScreenWidth() / 2f) {
                                animationBubble(getBubbleSpacing(), upItemY);
                            } else {
                                animationBubble(getScreenWidth() - imageView.getWidth() - getBubbleSpacing(), upItemY);
                            }
                        }
                }
                return true;
            }
        });
    }

    private void onBubbleClick(RoundedImageView imageView) {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(this);
            popupWindow.setWidth(getScreenWidth());
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            popupWindow.setOutsideTouchable(true);
            View inflate = LayoutInflater.from(this).inflate(R.layout.bubble_expanable, null);
            popupWindow.setContentView(inflate);
        }
        popupWindow.showAsDropDown(imageView, 0, -imageView.getBottom(), Gravity.BOTTOM);
    }

    private float getBubbleSpacing() {
        return convertDpToPixel(8, TestActivity.this);
    }

    private void animationBubble(float toX, float toY) {
        imageView.animate().cancel();
        imageView.animate().setDuration(500)
                .setInterpolator(new OvershootInterpolator())
                .x(toX)
                .y(toY)
                .start();
    }

    private int getScreenWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    private int getScreenHeight() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
