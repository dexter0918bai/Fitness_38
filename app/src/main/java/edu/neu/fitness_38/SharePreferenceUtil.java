package edu.neu.fitness_38;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

public class SharePreferenceUtil {
    private static final String FILE_NAME = "fitness";
    private static SharePreferenceUtil mInstance;

    private SharePreferenceUtil() {
    }

    public static SharePreferenceUtil getInstance() {
        if (mInstance == null) {
            synchronized (SharePreferenceUtil.class) {
                if (mInstance == null) {
                    mInstance = new SharePreferenceUtil();
                }
            }
        }
        return mInstance;
    }


    public void put(Context context, String key, Object value) {
        String type = value.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) value);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) value);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) value);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) value);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) value);
        }
        editor.apply();
    }

    /**
     * 读取键的值，若无则返回默认值
     *
     * @param context
     * @param key
     * @param defValue 默认值
     * @return
     */
    @Nullable
    public Object get(Context context, String key, Object defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String type = defValue.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defValue);
        } else if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defValue);
        }
        return null;
    }
}
