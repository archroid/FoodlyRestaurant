package ir.archroid.foodlyrestaurant.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Objects;

import ir.archroid.foodlyrestaurant.R;


public class RegisterFragment extends Fragment {
    private TextView btn_switch;


    public RegisterFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        btn_switch = view.findViewById(R.id.btn_register);
        btn_switch.setOnClickListener(v -> {
            LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity())).sendBroadcast(new Intent("login_switch"));
        });
        return view;
    }
}