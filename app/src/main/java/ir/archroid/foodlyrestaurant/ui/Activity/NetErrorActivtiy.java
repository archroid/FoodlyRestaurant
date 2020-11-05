package ir.archroid.foodlyrestaurant.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import ir.archroid.foodlyrestaurant.Data.Controller.TestNetworkController;
import ir.archroid.foodlyrestaurant.Data.FoodlyApi;
import ir.archroid.foodlyrestaurant.Helper.SnackBarHelper;
import ir.archroid.foodlyrestaurant.R;

public class NetErrorActivtiy extends AppCompatActivity {

    private AppCompatButton btn_retry;

    private ImageView iv_dnd;
    private ImageView iv_wifi;


    private CoordinatorLayout coordinatorLayout;

    private static FoodlyApi.TestNetworkCallback testNetworkCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_error_activtiy);


        btn_retry = findViewById(R.id.btn_retry);

        iv_dnd = findViewById(R.id.iv_dnd);
        iv_wifi = findViewById(R.id.iv_wifi);
        coordinatorLayout = findViewById(R.id.coordinator);

        Animation animation1 = AnimationUtils.loadAnimation(NetErrorActivtiy.this, R.anim.wave_1);
        Animation animation2 = AnimationUtils.loadAnimation(NetErrorActivtiy.this, R.anim.wave_2);

        iv_dnd.startAnimation(animation1);
        iv_wifi.startAnimation(animation2);
        btn_retry.setOnClickListener(view1 -> {
            testNetworkCallback = new FoodlyApi.TestNetworkCallback() {
                @Override
                public void onResponse(Boolean isSuccessful) {
                    if (isSuccessful) {
                        startActivity(new Intent(NetErrorActivtiy.this, MainActivity.class));
                    } else {
                        SnackBarHelper.alert(NetErrorActivtiy.this, coordinatorLayout, "Failed!");
                    }
                }

                @Override
                public void onFailure(String cause) {
                    SnackBarHelper.alert(NetErrorActivtiy.this, coordinatorLayout, "Failed!");
                }
            };

            TestNetworkController testNetworkController = new TestNetworkController(testNetworkCallback);
            testNetworkController.start();
        });

    }

}