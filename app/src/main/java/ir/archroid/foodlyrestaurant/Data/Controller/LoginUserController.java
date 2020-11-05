package ir.archroid.foodlyrestaurant.Data.Controller;

import com.google.gson.Gson;

import ir.archroid.foodlyrestaurant.Data.FoodlyApi;
import ir.archroid.foodlyrestaurant.Model.Token;
import ir.archroid.foodlyrestaurant.Model.Error;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginUserController {
    FoodlyApi.LoginUserCallback loginUserCallback;

    public LoginUserController(FoodlyApi.LoginUserCallback loginUserCallback) {
        this.loginUserCallback = loginUserCallback;
    }

    public void start(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FoodlyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FoodlyApi foodlyApi = retrofit.create(FoodlyApi.class);
        Call<Token> call = foodlyApi.loginUser(username, password);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    loginUserCallback.onResponse(true, null, response.body());
                } else {
                    try {
                        Gson gson = new Gson();
                        loginUserCallback.onResponse(false, gson.fromJson(response.errorBody().string(), Error.class).getError(), null);

                    } catch (Exception e) {

                    }

                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                loginUserCallback.onFailure(t.getMessage());
            }
        });
    }
}