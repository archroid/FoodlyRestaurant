package ir.archroid.foodlyrestaurant.Helper;

import android.content.Context;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import ir.archroid.foodlyrestaurant.R;

public class SnackBarHelper {

    public SnackBarHelper() {
    }
    public static void info(Context context , CoordinatorLayout coordinatorLayout , String text ){
        Snackbar snackbar = Snackbar.make(coordinatorLayout , text , BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setTextColor(context.getResources().getColor(R.color.foreground));
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.blue));
        snackbar.show();
    }
    public static void warning(Context context , CoordinatorLayout coordinatorLayout , String text ){
        Snackbar snackbar = Snackbar.make(coordinatorLayout , text , BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setTextColor(context.getResources().getColor(R.color.foreground));
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.yellow));
        snackbar.show();
    }
    public static void alert(Context context , CoordinatorLayout coordinatorLayout , String text ){
        Snackbar snackbar = Snackbar.make(coordinatorLayout , text , BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setTextColor(context.getResources().getColor(R.color.foreground));
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.red));
        snackbar.show();
    }
    public static void message(Context context ,CoordinatorLayout coordinatorLayout , String text ){
        Snackbar snackbar = Snackbar.make(coordinatorLayout , text , BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setTextColor(context.getResources().getColor(R.color.foreground));
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.green));
        snackbar.show();
    }
}
