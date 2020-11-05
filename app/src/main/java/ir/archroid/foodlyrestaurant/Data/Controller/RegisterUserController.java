package ir.archroid.foodlyrestaurant.Data.Controller;

import com.google.gson.Gson;
import ir.archroid.foodlyrestaurant.Data.FoodlyApi;
import ir.archroid.foodlyrestaurant.Model.Error;
import ir.archroid.foodlyrestaurant.Model.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUserController {

    FoodlyApi.RegisterUserCallback registerUserCallback;

    public RegisterUserController(FoodlyApi.RegisterUserCallback registerUserCallback) {
        this.registerUserCallback = registerUserCallback;
    }

    public void start(String username, String password, String email, String role, String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FoodlyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FoodlyApi foodlyApi = retrofit.create(FoodlyApi.class);
        Call<Token> call = foodlyApi.registerUser(username, password, email, role, city);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    registerUserCallback.onResponse(true, null, response.body());
                } else {
                    Gson gson = new Gson();
                    try {
                        registerUserCallback.onResponse(false, gson.fromJson(response.errorBody().string(), Error.class).getError(), null);
                    } catch (Exception e) {

                    }

                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                registerUserCallback.onFailure(t.getMessage());
            }
        });

    }
}