package com.study.wuyudong.wyd.app;

import android.app.Application;
import android.content.Context;

import com.socks.library.KLog;
import com.study.wuyudong.wyd.utils.CrashHandler;
import com.study.wuyudong.wyd.utils.MyLog;

public class App extends Application {

    private static final String TAG = App.class.getSimpleName();
    private static final String Log_Tag = "com.study";

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        AppCache.setContext(this);
        KLog.init(true, Log_Tag);
        MyLog.initLog(true,true,true,3,5*1024,null,Log_Tag);
//        MyLog.initLog(true,Log_Tag);
        MyLog.d("APP 66666666666666666666666");
        KLog.d("APP 66666666666666666666666");
    }

    //获取全局context对象
    public static Context getContext() {
        return mContext;
    }
}
