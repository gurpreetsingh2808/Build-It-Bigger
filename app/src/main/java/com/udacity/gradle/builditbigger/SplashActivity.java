package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final TextView tvLetsBuildIt = (TextView) findViewById(R.id.tvLetsBuildIt);
        final TextView tvBigger = (TextView) findViewById(R.id.tvBigger);


        YoYo.with(Techniques.StandUp)
                .delay(500)
                .interpolate(new DecelerateInterpolator())
                .duration(1000)
                .withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (tvLetsBuildIt != null) {
                    tvLetsBuildIt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                YoYo.with(Techniques.ZoomIn).playOn(tvBigger);

                if (tvBigger != null) {
                    tvBigger.setVisibility(View.VISIBLE);
                }
                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                }, 1800);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).playOn(tvLetsBuildIt);

    }
}
