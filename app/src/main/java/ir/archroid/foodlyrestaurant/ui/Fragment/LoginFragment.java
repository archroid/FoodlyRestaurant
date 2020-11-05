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

import ir.archroid.foodlyrestaurant.Data.Controller.LoginUserController;
import ir.archroid.foodlyrestaurant.Data.FoodlyApi;
import ir.archroid.foodlyrestaurant.Helper.MyPreferenceManager;
import ir.archroid.foodlyrestaurant.Helper.SnackBarHelper;
import ir.archroid.foodlyrestaurant.Model.Token;
import ir.archroid.foodlyrestaurant.R;


public class LoginFragment extends Fragment {

    private TextView btn_switch;
    private RelativeLayout btn_submit;

    private EditText et_username, et_password;

    private String username, password;

    private CoordinatorLayout coordinatorLayout;

    private FoodlyApi.LoginUserCallback loginUserCallback;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btn_switch = view.findViewById(R.id.btn_register);
        btn_submit = view.findViewById(R.id.btn_submit);

        coordinatorLayout = view.findViewById(R.id.coordinator);

        et_username = view.findViewById(R.id.et_username);
        et_password = view.findViewById(R.id.et_passowrd);

        btn_switch.setOnClickListener(v -> LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity())).sendBroadcast(new Intent("register_switch")));

        btn_submit.setOnClickListener(v -> {
            username = et_username.getText().toString();
            password = et_password.getText().toString();

            LoginUserController loginUserController = new LoginUserController(loginUserCallback);

            if (isValid()){
                loginUserController.start(username, password);
            }
        });


        //Login User Callback
        loginUserCallback = new FoodlyApi.LoginUserCallback() {
            @Override
            public void onResponse(Boolean isSuccessful, String errorMSG, Token token) {

                if (isSuccessful) {
                    MyPreferenceManager.getInstance(getActivity()).putAccessToken(token.getToken());
                    MyPreferenceManager.getInstance(getActivity()).putUsername(username);
                    LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity())).sendBroadcast(new Intent("main_switch"));
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

        return true;
    }
}