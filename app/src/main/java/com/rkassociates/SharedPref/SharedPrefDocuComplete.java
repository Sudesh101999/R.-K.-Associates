package com.rkassociates.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefDocuComplete {
    private static SharedPrefDocuComplete mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "RK_Shared_Pref_Auth";
    private static final String KEY_USER_ID = "key_userid";
    private static final String KEY_DOC_REGISTERED_USER = "key_registered_user";
    private static final String KEY_DOC_PROFILE = "key_doc_profile";
    private static final String KEY_DOC_BUSINESS_VERIF = "key_doc_business_verif";
    private static final String KEY_DOC_ITR_AND_KYC_VERIF = "key_doc_itr_and_kyc_verif";
    private static final String KEY_DOC_CURRENT_RESI_VERIF = "key_doc_current_residence_verif";
    private static final String KEY_DOC_TELE_VERIF_OF_REFER = "key_doc_tele_verif_of_refer";
    private static final String KEY_DOC_CURRENT_RESIDENCE_PROP = "key_doc_current_residence_prop";
    private static final String KEY_DOC_DETAILS_OF_ITR = "key_doc_details_of_itr";
    private static final String KEY_DOC_ASSETS_VERIF = "key_doc_assets_verification";
    private static final String KEY_DOC_UPLOAD_IMAGE = "key_doc_uplaod_image";
    private static final String KEY_DOC_DEALER_CAR = "key_doc_dealer_car";

    private SharedPrefDocuComplete(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefDocuComplete getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefDocuComplete(context);
        }
        return mInstance;
    }

    //set and get Registered data
    public void setRegistereduserData(boolean registeredUserDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_REGISTERED_USER, registeredUserDone);
        editor.apply();
    }
    public boolean getRegistereduserData(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_REGISTERED_USER, false);
        return isCandidateStr;
    }
    //set and get Profile data
    public void setProfileData(boolean profileDataDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_PROFILE, profileDataDone);
        editor.apply();
    }
    public boolean getProfileData(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_PROFILE, false);
        return isCandidateStr;
    }
    //set and get Business data
    public void setBusiness(boolean businessDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_BUSINESS_VERIF, businessDone);
        editor.apply();
    }
    public boolean getBusiness(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_BUSINESS_VERIF, false);
        return isCandidateStr;
    }
    //set and get ItrAndKycVerif data
    public void setItrAndKycVerif(boolean itrKycVerifDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_ITR_AND_KYC_VERIF, itrKycVerifDone);
        editor.apply();
    }
    public boolean getItrAndKycVerif(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_ITR_AND_KYC_VERIF, false);
        return isCandidateStr;
    }
    //set and get CurrentResidence data
    public void setCurrentResidence(boolean currentResidenceDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_CURRENT_RESI_VERIF, currentResidenceDone);
        editor.apply();
    }
    public boolean getCurrentResidenceVerif(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_CURRENT_RESI_VERIF, false);
        return isCandidateStr;
    }
    //set and get TeleVerif data
    public void setTeleVerif(boolean teleVerifDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_TELE_VERIF_OF_REFER, teleVerifDone);
        editor.apply();
    }
    public boolean getTeleVerif(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_TELE_VERIF_OF_REFER, false);
        return isCandidateStr;
    }
    //set and get CurrentResidProp data
    public void setCurrentResidProp(boolean currentResidPropDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_CURRENT_RESIDENCE_PROP, currentResidPropDone);
        editor.apply();
    }
    public boolean getCurrentResidProp(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_CURRENT_RESIDENCE_PROP, false);
        return isCandidateStr;
    }
    //set and get Profile data
    public void setDetailsOfITR(boolean detailsOfItrDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_DETAILS_OF_ITR, detailsOfItrDone);
        editor.apply();
    }
    public boolean getDetailsOfItr(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_DETAILS_OF_ITR, false);
        return isCandidateStr;
    }
    //set and get Assets verif data
    public void setAssetsVerif(boolean assetsVerifDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_ASSETS_VERIF, assetsVerifDone);
        editor.apply();
    }
    public boolean getAssetsVerif(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_ASSETS_VERIF, false);
        return isCandidateStr;
    }
    //set and get Upload image data
    public void setUploadImage(boolean uploadImageDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_UPLOAD_IMAGE, uploadImageDone);
        editor.apply();
    }
    public boolean getUploadImage(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_UPLOAD_IMAGE, false);
        return isCandidateStr;
    }
    //set and get dealerCar data
    public void setDealerCar(boolean dealerCarDone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_DOC_DEALER_CAR, dealerCarDone);
        editor.apply();
    }
    public boolean getDealerCar(Context context){
        SharedPreferences candidateSf;
        boolean isCandidateStr;
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getBoolean(KEY_DOC_DEALER_CAR, false);
        return isCandidateStr;
    }

    public boolean clearDocData() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
