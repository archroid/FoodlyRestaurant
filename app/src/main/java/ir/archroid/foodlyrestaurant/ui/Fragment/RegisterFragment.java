package ir.archroid.foodlyrestaurant.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.Objects;
import ir.archroid.foodlyrestaurant.Data.Controller.RegisterUserController;
import ir.archroid.foodlyrestaurant.Data.FoodlyApi;
import ir.archroid.foodlyrestaurant.Helper.SnackBarHelper;
import ir.archroid.foodlyrestaurant.Model.Token;
import ir.archroid.foodlyrestaurant.R;


public class RegisterFragment extends Fragment {
    private TextView btn_switch;
    private RelativeLayout btn_submit;

    private CoordinatorLayout coordinatorLayout;

    private EditText et_username, et_email, et_password, et_confirm;

    private String username, password, confirm, email;

    private FoodlyApi.RegisterUserCallback registerUserCallback;


    public RegisterFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        btn_switch = view.findViewById(R.id.btn_switch);
        btn_submit = view.findViewById(R.id.btn_submit);

        coordinatorLayout = view.findViewById(R.id.coordinator);

        et_username = view.findViewById(R.id.et_username);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_passowrd);
        et_confirm = view.findViewById(R.id.et_confirm);


        btn_switch.setOnClickListener(v -> LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity())).sendBroadcast(new Intent("login_switch")));


        btn_submit.setOnClickListener(view1 -> {

            username = et_username.getText().toString();
            password = et_password.getText().toString();
            confirm = et_confirm.getText().toString();
            email = et_email.getText().toString();

            if (isValid()) {
                RegisterUserController registerUserController = new RegisterUserController(registerUserCallback);
                registerUserController.start(
                        username,
                        password,
                        email,
    

                );
            }


        });


        // Callback ==> Get Result
        registerUserCallback = new FoodlyApi.RegisterUserCallback() {
            @Override
            public void onResponse(Boolean isSuccessful, String errorMSG, Token token) {
                if (isSuccessful) {
                    LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity())).sendBroadcast(new Intent("login_switch"));
                } else {
                    SnackBarHelper.alert(Objects.requireNonNull(getActivity()), coordinatorLayout, errorMSG);
                }
            }

            @Override
            public void onFailure(String cause) {
                SnackBarHelper.alert(Objects.requireNonNull(getActivity()), coordinatorLayout, cause);
            }
        };


        return view;
    }

    private Boolean isValid() {
        if (email.isEmpty()) {
            SnackBarHelper.alert(Objects.requireNonNull(getActivity()), coordinatorLayout, "Not valid email!");
            et_email.setError("Not valid email!");
            return false;
        }
        if (username.isEmpty()) {
            SnackBarHelper.alert(Objects.requireNonNull(getActivity()), coordinatorLayout, "Not valid username!");
            et_username.setError("Not valid username!");
            return false;
        }
        if (password.isEmpty()) {
            SnackBarHelper.alert(Objects.requireNonNull(getActivity()), coordinatorLayout, "Not valid password!");
            et_password.setError("Not valid password!");
            return false;
        }
        if (!confirm.equals(password)) {
            SnackBarHelper.alert(Objects.requireNonNull(getActivity()), coordinatorLayout, "Passwords do not match!");
            return false;
        }
        return true;
    }
}