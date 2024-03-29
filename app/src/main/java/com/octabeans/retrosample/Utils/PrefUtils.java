package com.octabeans.retrosample.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    public PrefUtils() {
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("APP_PREF", Context.MODE_PRIVATE);
    }

    public static void storeApiKey(Context context, String apiKey) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("API_KEY", apiKey);
        editor.apply();
    }

    public static String getApiKey(Context context) {
        return getSharedPreferences(context).getString("API_KEY", "APR20191001");
    }

    public static String getAccessKey(Context context) {
        return getSharedPreferences(context).getString("ACCESS_KEY", null);
    }

}
