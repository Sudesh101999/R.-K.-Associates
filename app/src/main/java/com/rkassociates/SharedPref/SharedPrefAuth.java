package com.rkassociates.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefAuth {

    private static SharedPrefAuth mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "RK_Shared_Pref";
    private static final String KEY_USER_ID = "key_userid";
    private static final String KEY_USER_NAME = "key_username";
    private static final String KEY_USER_EMAIL = "key_email";
    private static final String KEY_USER_IMAGE_URI = "key_user_image_uri";
    private static final String KEY_USER_PHONE = "key_user_phone";
    private static final String KEY_APLC_NAME = "key_aplc_name";
    private static final String KEY_APLC_ID = "key_aplc_id";
    private static final String KEY_CO_APLC_NAME = "key_co_aplc_name";
    private static final String KEY_APLC_YEAR_1 = "key_aplc_year_1";
    private static final String KEY_APLC_YEAR_2 = "key_aplc_year_2";
    private static final String KEY_APLC_YEAR_3 = "key_aplc_year_3";
    private static final String KEY_ADD_DATA_PAGE_ID = "key_add_data_page_id";

    private SharedPrefAuth(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefAuth getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefAuth(context);
        }
        return mInstance;
    }


    public boolean userLogin(String nameStr, String userIdStr, String emailStr,String phoneStr) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ID, userIdStr);
        editor.putString(KEY_USER_NAME, nameStr);
        editor.putString(KEY_USER_EMAIL, emailStr);
        editor.putString(KEY_USER_PHONE, phoneStr);
        editor.apply();
        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_ID, null) != null)
            return true;
        else
            return false;
    }

    public String getValueOfUserId(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_USER_ID, null);
        return text;
    }

    public String getUserName(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_USER_NAME, null);
        return text;
    }
    public String getProfileImage(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_USER_IMAGE_URI, null);
        return text;
    }

    public void setProfileImage(Context context, String image_url) {
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(KEY_USER_IMAGE_URI,image_url);
        editor.apply();
    }

    public void setAplcId(Context context, String aplcIdStr) {
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(KEY_APLC_ID,aplcIdStr);
        editor.apply();
    }
    public String getAplcId(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_APLC_ID, null);
        return text;
    }
    public void setCoAplcName(Context context, String coAplcNameStr) {
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(KEY_CO_APLC_NAME,coAplcNameStr);
        editor.apply();
    }

    public String getCoAplcName(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_CO_APLC_NAME, null);
        return text;
    }
    public void setAplcItrYears(Context context, String year1,String year2,String year3) {
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(KEY_APLC_YEAR_1,year1);
        editor.putString(KEY_APLC_YEAR_2,year2);
        editor.putString(KEY_APLC_YEAR_3,year3);
        editor.apply();
    }

    public String getAplcItrYear1(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_APLC_YEAR_1, null);
        return text;
    }
    public String getAplcItrYear2(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_APLC_YEAR_2, null);
        return text;
    }
    public String getAplcItrYear3(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_APLC_YEAR_3, null);
        return text;
    }

    public void setAddDataPageId(Context context, String addDataPageId) {
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(KEY_ADD_DATA_PAGE_ID,addDataPageId);
        editor.apply();
    }

    public String getAddDataPageId(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_ADD_DATA_PAGE_ID, null);
        return text;
    }

    public boolean clearDocAuthData() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public int checkLoginStatus(Context context){
        SharedPreferences sharedPreferences1 = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        int login_status = sharedPreferences1.getInt("status", 0);
        return login_status;
    }

    public void setLoginStatus(Context context, int status) {
        SharedPreferences sharedPreferences =context.getSharedPreferences("login",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putInt("status",status);
        editor.apply();
    }

}
