package ir.archroid.foodlyrestaurant.Data.Controller;

import ir.archroid.foodlyrestaurant.Data.FoodlyApi;
import ir.archroid.foodlyrestaurant.Model.Status;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestNetworkController {
    FoodlyApi.TestNetworkCallback testNetworkCallback;

    public TestNetworkController(FoodlyApi.TestNetworkCallback testNetworkCallback) {
        this.testNetworkCallback = testNetworkCallback;
    }

    public void start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FoodlyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodlyApi foodlyApi = retrofit.create(FoodlyApi.class);
        Call<Status> call = foodlyApi.testNetwork();
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                testNetworkCallback.onResponse(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                testNetworkCallback.onFailure(t.getMessage());
            }
        });

    }
}
