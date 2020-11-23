package ir.archroid.foodlyrestaurant.Data;

import ir.archroid.foodlyrestaurant.Model.Status;
import ir.archroid.foodlyrestaurant.Model.Token;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FoodlyApi {
    String BASE_URL = "http://193.111.235.38/";


    @GET("ping")
    Call<Status> testNetwork();

    // Register API
    @FormUrlEncoded
    @POST("/register/restaurant")
    Call<Token> registerUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("city") String city,
            @Field("kind") String kind,
            @Field("address") String address,
            @Field("name") String name,
            @Field("desc") String desc
    );

    // Login API
    @FormUrlEncoded
    @POST("login/restaurant")
    Call<Token> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );


    interface TestNetworkCallback {
        void onResponse(Boolean isSuccessful);

        void onFailure(String cause);
    }


    interface LoginUserCallback {
        void onResponse(Boolean isSuccessful, String errorMSG, Token token);

        void onFailure(String cause);
    }

    interface RegisterUserCallback {
        void onResponse(Boolean isSuccessful, String errorMSG, Token token);

        void onFailure(String cause);
    }
}
