package ir.archroid.foodlyrestaurant.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import ir.archroid.foodlyrestaurant.R;

public class SplashActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.ImageView);


        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash);
        imageView.startAnimation(animation);


        new CountDownTimer(1200, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this , AuthActivity.class));
                finish();
            }
        }.start();
    }
}