package com.mahmoudelshamy.ebg;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

import datamodels.Constants;
import utils.FontUtil;

/**
 * Created by Shamyyoun on 3/3/2015.
 */
public class AppController extends Application {
    public static final String END_POINT = "http://egypt-media.com/EBG/services/";
    public static final int SEARCH_RESULTS_LIMIT = 20;
    private int lang; // TODO test only

    public AppController() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // override default font
        FontUtil.setDefaultFont(getApplicationContext(), "MONOSPACE", "una_font.ttf");

        // load cached language
        String cachedLang = getCachedString(this, Constants.SP_SETTINGS, Constants.SP_KEY_LANG);
        if (cachedLang == null)
            // Arabic is default
            lang = Constants.LANG_AR;
        else if (cachedLang.equals("ar"))
            lang = Constants.LANG_AR;
        else if (cachedLang.equals("en"))
            lang = Constants.LANG_EN;

        // update it
        updateLanguage(lang);
    }

    /**
     * method used to return current application instance
     */
    public static AppController getInstance(Context context) {
        return (AppController) context;
    }

    /**
     * method, used to update app language
     */
    public void updateLanguage(int lang) {
        String strLang = (lang == Constants.LANG_AR ? "ar" : "en");

        // change locale settings
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(strLang);
        res.updateConfiguration(conf, dm);

        // cache current language
        updateCachedString(this, Constants.SP_SETTINGS, Constants.SP_KEY_LANG, strLang);

        // update runtime lang field
        this.lang = lang;
    }

    public int getLang() {
        return lang;
    }

    /**
     * method used to update events response in SP
     */
    public static void updateEventsResponse(Context context, String value) {
        updateCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_EVENTS_RESPONSE, value);
    }

    /**
     * method used to get events response from SP
     */
    public static String getEventsResponse(Context context) {
        String response = getCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_EVENTS_RESPONSE);

        return response;
    }

    /**
     * method used to update sectors response in SP
     */
    public static void updateSectorsResponse(Context context, String value) {
        updateCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_SECTORS_RESPONSE, value);
    }

    /**
     * method used to get sectors response from SP
     */
    public static String getSectorsResponse(Context context) {
        String response = getCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_SECTORS_RESPONSE);

        return response;
    }

    /**
     * method used to update sub sectors response in SP
     */
    public static void updateSubSectorsResponse(Context context, String sectorId, String value) {
        String spKey = "sector_" + sectorId + "_" + Constants.SP_KEY_SUB_SECTORS_RESPONSE;
        updateCachedString(context, Constants.SP_RESPONSES, spKey, value);
    }

    /**
     * method used to get sub sectors response from SP
     */
    public static String getSubSectorsResponse(Context context, String sectorId) {
        String spKey = "sector_" + sectorId + "_" + Constants.SP_KEY_SUB_SECTORS_RESPONSE;
        String response = getCachedString(context, Constants.SP_RESPONSES, spKey);

        return response;
    }

    /**
     * method used to update companies response in SP
     */
    public static void updateCompaniesResponse(Context context, String subSectorId, String value) {
        String spKey = "subsector_" + subSectorId + "_" + Constants.SP_KEY_COMPANIES_RESPONSE;
        updateCachedString(context, Constants.SP_RESPONSES, spKey, value);
    }

    /**
     * method used to get companies response from SP
     */
    public static String getCompaniesResponse(Context context, String subSectorId) {
        String spKey = "subsector_" + subSectorId + "_" + Constants.SP_KEY_COMPANIES_RESPONSE;
        String response = getCachedString(context, Constants.SP_RESPONSES, spKey);

        return response;
    }

    /**
     * method used to update string value in SP
     */
    private static void updateCachedString(Context context, String spName, String valueName, String value) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(valueName, value);
        editor.commit();
    }

    /**
     * method used to get cached String from SP
     */
    private static String getCachedString(Context context, String spName, String valueName) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        String value = sp.getString(valueName, null);

        return value;
    }
}
