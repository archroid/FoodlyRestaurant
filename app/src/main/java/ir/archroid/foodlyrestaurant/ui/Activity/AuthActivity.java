package ir.archroid.foodlyrestaurant.ui.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
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

    private RelativeLayout btn_login;
    private TextView btn_switch;

    private RegisterFragment registerFragment = new RegisterFragment();
    private LoginFragment loginFragment = new LoginFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mainLayout = findViewById(R.id.MainLayout);
        frameLayout = findViewById(R.id.frameLayout);
        btn_login = findViewById(R.id.login_button);
        btn_switch = findViewById(R.id.btn_switch);

        mainLayout.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.GONE);


        btn_login.setOnClickListener(v -> {
            mainLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, registerFragment).commit();
        });


        btn_switch.setOnClickListener(v -> {
            mainLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, loginFragment).commit();
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

    private BroadcastReceiver LoginBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mainLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, loginFragment).commit();
        }
    };

    private BroadcastReceiver RegisterBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mainLayout.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, registerFragment).commit();
        }
    };

    private BroadcastReceiver MainBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            startActivity(new Intent(AuthActivity.this, MainActivity.class));
            finish();
        }
    };

}