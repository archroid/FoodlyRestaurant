package ir.archroid.foodlyrestaurant.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import ir.archroid.foodlyrestaurant.Data.Controller.TestNetworkController;
import ir.archroid.foodlyrestaurant.Data.FoodlyApi;
import ir.archroid.foodlyrestaurant.Helper.MyPreferenceManager;
import ir.archroid.foodlyrestaurant.R;

public class SplashActivity extends AppCompatActivity {
    private ImageView imageView;

    private FoodlyApi.TestNetworkCallback testNetworkCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.ImageView);


        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash);
        imageView.startAnimation(animation);

        // Check Network Connection Callback
        testNetworkCallback = new FoodlyApi.TestNetworkCallback() {
            @Override
            public void onResponse(Boolean isSuccessful) {
                if (isSuccessful) {
                    if (MyPreferenceManager.getInstance(SplashActivity.this).getAccessToken() != null) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                    }
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, NetErrorActivtiy.class));
                    finish();
                }
            }

            @Override
            public void onFailure(String cause) {
                startActivity(new Intent(SplashActivity.this, NetErrorActivtiy.class));
                finish();
            }
        };

        TestNetworkController testNetworkController = new TestNetworkController(testNetworkCallback);

        new CountDownTimer(1200, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                testNetworkController.start();
            }
        }.start();
    }
}