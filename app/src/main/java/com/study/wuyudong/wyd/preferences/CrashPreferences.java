package com.study.wuyudong.wyd.preferences;
/**
 * author:wuyd
 * date:2019/1/14
 * 判断是否上传日志到服务器用
 */
import android.content.Context;
import android.content.SharedPreferences;

import com.study.wuyudong.wyd.app.AppCache;

public class CrashPreferences {
    private static final String KEY_CRASH="false";
    public static void saveKeyCrash(String b){
        saveString(KEY_CRASH,b);
    }

    public static String getKeyCrash(){
        return getBoolean(KEY_CRASH);
    }
    private static void saveString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getBoolean(String key) {
        return getSharedPreferences().getString(key,null);
    }

    static SharedPreferences getSharedPreferences() {
        return AppCache.getContext().getSharedPreferences("CrashPreferences", Context.MODE_PRIVATE);
    }
}
