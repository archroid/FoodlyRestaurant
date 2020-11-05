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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import ir.archroid.foodlyrestaurant.R;
import ir.archroid.foodlyrestaurant.ui.Fragment.LoginFragment;
import ir.archroid.foodlyrestaurant.ui.Fragment.RegisterFragment;

public class AuthActivity extends AppCompatActivity {

    private RelativeLayout mainLayout;
    private FrameLayout frameLayout;

    private TextView hello_restaurant;

    private RelativeLayout btn_login;
    private TextView btn_switch;

    private final RegisterFragment registerFragment = new RegisterFragment();
    private final LoginFragment loginFragment = new LoginFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        hello_restaurant = findViewById(R.id.hello_restaurant);

        Animation animation1 = AnimationUtils.loadAnimation(AuthActivity.this, R.anim.wave_2);
        hello_restaurant.setAnimation(animation1);

        mainLayout = findViewById(R.id.MainLayout);
        frameLayout = findViewById(R.id.frameLayout);
        btn_login = findViewById(R.id.login_button);
        btn_switch = findViewById(R.id.btn_switch);

        mainLayout.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.GONE);


        btn_login.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(AuthActivity.this, R.anim.fade_out);
            mainLayout.setAnimation(animation);
            mainLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.fade_in,
                            R.anim.fade_out
//                            R.anim.fade_in,
//                            R.anim.slide_out_right
                    )
                    .replace(R.id.frameLayout, registerFragment).addToBackStack("a").commit();
        });


        btn_switch.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(AuthActivity.this, R.anim.fade_out);
            mainLayout.setAnimation(animation);
            mainLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.fade_in,
                            R.anim.fade_out
//                            R.anim.fade_in,
//                            R.anim.slide_out_right
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
            mainLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_left,
//                            R.anim.fade_out,
//                            R.anim.fade_in,
                            R.anim.slide_out_left
                    )
                    .replace(R.id.frameLayout, loginFragment).commit();
        }
    };

    private final BroadcastReceiver RegisterBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mainLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_right,
//                            R.anim.fade_out,
//                            R.anim.fade_in,
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
        mainLayout.setAnimation(animation2);
        mainLayout.setVisibility(View.VISIBLE);

        try {
            getSupportFragmentManager().beginTransaction().remove(registerFragment).commit();
            getSupportFragmentManager().beginTransaction().remove(loginFragment).commit();
        } catch (Exception e) {

        }
    }
}