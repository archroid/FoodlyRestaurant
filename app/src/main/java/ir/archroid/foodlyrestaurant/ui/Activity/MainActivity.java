package ir.archroid.foodlyrestaurant.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ir.archroid.foodlyrestaurant.R;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btn_submitFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_submitFood = findViewById(R.id.btn_submitFood);

        btn_submitFood.setOnClickListener(v -> {
           startActivity(new Intent(MainActivity.this, SubmitFoodActivity.class));
        });
    }
}