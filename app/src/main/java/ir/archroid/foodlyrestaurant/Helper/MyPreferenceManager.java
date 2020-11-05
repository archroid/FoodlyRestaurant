package ir.archroid.foodlyrestaurant.Helper;


import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferenceManager {

    private static MyPreferenceManager instance = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    public static MyPreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new MyPreferenceManager(context);
        }
        return instance;
    }

    private MyPreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences("foodlyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }


    public String getUsername() {
        return sharedPreferences.getString("username", null);
    }

    public String getAccessToken() {
        return sharedPreferences.getString("accessToken", null);
    }

    public void putUsername(String username) {
        editor.putString("username", username).apply();
    }

    public void putAccessToken(String accessToken) {
        editor.putString("accessToken", accessToken).apply();
    }


}