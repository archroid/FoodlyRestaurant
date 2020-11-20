package ir.archroid.foodlyrestaurant.ui.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import ir.archroid.foodlyrestaurant.R;
import ir.archroid.foodlyrestaurant.ui.Fragment.LoginFragment;
import ir.archroid.foodlyrestaurant.ui.Fragment.RegisterFragment;

public class AuthActivity extends AppCompatActivity {

    private AppCompatButton btn_login, btn_register;
    private ImageView iv_pizza;

    private FrameLayout frameLayout;
    private ConstraintLayout constraintLayout;


    private final RegisterFragment registerFragment = new RegisterFragment();
    private final LoginFragment loginFragment = new LoginFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        iv_pizza = findViewById(R.id.iv_pizza);

        frameLayout = findViewById(R.id.frameLayout);
        constraintLayout = findViewById(R.id.constraintLayout);

        Animation animation1 = AnimationUtils.loadAnimation(AuthActivity.this, R.anim.wave_2);
        iv_pizza.setAnimation(animation1);


        constraintLayout.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.GONE);


        btn_login.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(AuthActivity.this, R.anim.fade_out);
            constraintLayout.setAnimation(animation);
            constraintLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.fade_in,
                            R.anim.fade_out
                    )
                    .replace(R.id.frameLayout, registerFragment).addToBackStack("a").commit();
        });


        btn_register.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(AuthActivity.this, R.anim.fade_out);
            constraintLayout.setAnimation(animation);

            constraintLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);

            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.fade_in,
                            R.anim.fade_out
                    )
                    .replace(R.id.frameLayout, registerFragment).addToBackStack("a").commit();
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(AuthActivity.this).unregisterReceiver(LoginBroadcastReceiver);
        LocalBroadcastManager.getInstance(AuthActivity.this).unregisterReceiver(RegisterBroadcastReceiver);
        LocalBroadcastManager.getInstance(AuthActivity.this).unregisterReceiver(MainBroadcastReceiver);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(AuthActivity.this).registerReceiver(LoginBroadcastReceiver, new IntentFilter("login_switch"));
        LocalBroadcastManager.getInstance(AuthActivity.this).registerReceiver(RegisterBroadcastReceiver, new IntentFilter("register_switch"));
        LocalBroadcastManager.getInstance(AuthActivity.this).registerReceiver(MainBroadcastReceiver, new IntentFilter("main_switch"));
    }

    private final BroadcastReceiver LoginBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            constraintLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_left,
                            R.anim.slide_out_left
                    )
                    .replace(R.id.frameLayout, loginFragment).commit();
        }
    };

    private final BroadcastReceiver RegisterBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            constraintLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_right,
                            R.anim.slide_out_right
                    )
                    .replace(R.id.frameLayout, registerFragment).commit();
        }
    };

    private final BroadcastReceiver MainBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            startActivity(new Intent(AuthActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animation animation = AnimationUtils.loadAnimation(AuthActivity.this, R.anim.fade_out);
        frameLayout.setAnimation(animation);
        frameLayout.setVisibility(View.GONE);
        Animation animation2 = AnimationUtils.loadAnimation(AuthActivity.this, R.anim.fade_in);
        constraintLayout.setAnimation(animation2);
        constraintLayout.setVisibility(View.VISIBLE);

        try {
            getSupportFragmentManager().beginTransaction().remove(registerFragment).commit();
            getSupportFragmentManager().beginTransaction().remove(loginFragment).commit();
        } catch (Exception e) {

        }
    }
}