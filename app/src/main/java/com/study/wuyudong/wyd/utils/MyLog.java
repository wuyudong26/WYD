package com.study.wuyudong.wyd.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.socks.library.KLog;
import com.study.wuyudong.wyd.app.App;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

/**
 * author:wuyd
 * date:2019/1/14
 * description:自定义打印日志并且存到本地
 */
@SuppressLint("SimpleDateFormat")
public class MyLog {
    /**
     * 全局tag相关
     */
    private static String mGlobalTag;
    private static final String TAG_DEFAULT = "MyLog";
    private static boolean mIsGlobalTagEmpty = true;
    private static String tag1;

    private static final String SUFFIX = ".java";
    /**
     * 存储日志的文件名
     */
    private static String FILE_NAME;
    private static Boolean isRequireNewFile = true;
    /**
     * 存储的日志文件总个数
     */
    private static int FILE_NUMBERS;
    private static int DEFAULT_FILE_NUMBERS=1;//默认文件个数1个
    /**
     * 存储的每个日志文件大小
     */
    private static int FILES_SIZE;
    private static int DEFAULT_FILES_SIZE=50*1024*1024;//默认50M
    /**
     * 日志文件总开关
     */
    private static Boolean MY_LOG_SWITCH =false;

    /**
     * 日志打印文件开关
     */
    private static Boolean MY_LOG_PRINT_SWITCH =false;
    /**
     * 日志写入文件开关
     */
    private static Boolean MY_LOG_WRITE_TO_FILE=false ;
    /**
     * 日志文件的路径
     */
    public static String MY_LOG_PATH = getFilePath(App.getContext());
    /**
     * sd卡中日志文件的最多保存天数
     */
    private static int SDCARD_LOG_FILE_SAVE_DAYS = 0;
    /**
     * 日志的输出格式
     */
    private static SimpleDateFormat myLogSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 日志文件格式
     */
    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");

    public static void v(String msg) {
        log(msg, "v",null);
    }
    public static void v(String tag,String msg) {
        log(msg, "v",tag);
    }

    public static void d(String msg) {
        log(msg, "d",null);
    }
    public static void d(String tag,String msg) {
        log(msg, "d",tag);
    }

    public static void i(String msg) {
        log(msg, "i",null);
    }
    public static void i(String tag,String msg) {
        log(msg, "i",tag);
    }

    public static void w(String msg) {
        log(msg, "w",null);
    }
    public static void w(String tag,String msg) {
        log(msg, "w",tag);
    }

    public static void e(String msg) {
        log(msg, "e",null);
    }
    public static void e(String tag,String msg) {
        log(msg, "e",tag);
    }


    private static void log(String msg, String level,String tag) {
        if(MY_LOG_SWITCH) {
            String[] info = infos();
            if (MY_LOG_PRINT_SWITCH) {
                String className = info[0];
                String methodName = info[1];
                String lineNumber = info[2];
                tag1 = (tag == null ? className : tag);
                if (mIsGlobalTagEmpty && TextUtils.isEmpty(tag)) {
                    tag1 = TAG_DEFAULT;
                } else if (!mIsGlobalTagEmpty) {
                    tag1 = mGlobalTag;
                }
                StringBuilder sb = new StringBuilder()
                        .append(tag1+": ")
                        .append("[ ")
                        .append("(")
                        .append(className)
                        .append(":")
                        .append(lineNumber)
                        .append(")")
                        .append("#")
                        .append(methodName)
                        .append(" ]");

                switch (level) {
                    case "v":
                        Log.v(sb.toString(), msg);
                        break;
                    case "d":
                        Log.d(sb.toString(), msg);
                        break;
                    case "i":
                        Log.i(sb.toString(), msg);
                        break;
                    case "w":
                        Log.w(sb.toString(), msg);
                        break;
                    case "e":
                        Log.e(sb.toString(), msg);
                        break;
                    default:
                        break;
                }
            }
            if (MY_LOG_WRITE_TO_FILE) {
                writeLogToFile(level, info, msg);
            }
        }
    }
    /**
     * 开启打印开关并存到本地
     */
    public static void initLog(Boolean isMyLogSwitch,Boolean isMyLogPrintSwitch,Boolean isMyLogWriteToFile,int fileNumbers,int filesSize,String filaName,String tag) {
        MY_LOG_SWITCH=isMyLogSwitch;
        MY_LOG_PRINT_SWITCH=isMyLogPrintSwitch;
        MY_LOG_WRITE_TO_FILE = isMyLogWriteToFile;
        FILE_NUMBERS=fileNumbers;
        FILES_SIZE=filesSize;
        FILE_NAME=filaName;
        mGlobalTag = tag;
        mIsGlobalTagEmpty = TextUtils.isEmpty(mGlobalTag);
    }
    public static void initLog(Boolean isMyLogSwitch,String tag){
        MY_LOG_SWITCH=isMyLogSwitch;
        MY_LOG_PRINT_SWITCH=isMyLogSwitch;
        MY_LOG_WRITE_TO_FILE=isMyLogSwitch;
        mGlobalTag = tag;
        mIsGlobalTagEmpty = TextUtils.isEmpty(mGlobalTag);
    }
    public static void initLog(Boolean isMyLogSwitch){
        MY_LOG_SWITCH=isMyLogSwitch;
        MY_LOG_PRINT_SWITCH=isMyLogSwitch;
        MY_LOG_WRITE_TO_FILE=isMyLogSwitch;
    }

    /**
     * 获得打印信息所在类名、方法名、行号等信息
     *
     * @return className, methodName, lineNumber
     */
    private static String[] infos() {
        getFilePath(App.getContext());
        String[] infos = new String[]{"", "", ""};
        StackTraceElement element = new Throwable().getStackTrace()[3];
        String className=element.getClassName();
        String methodName=element.getMethodName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }
        if (className.contains("$")) {
            className = className.split("\\$")[0] + SUFFIX;
        }
        int lineNumber = element.getLineNumber();

        if (lineNumber < 0) {
            lineNumber = 0;
        }
        infos[0] = className;
        infos[1] =methodName;
        infos[2] = String.valueOf(lineNumber);
        return infos;

    }

    /**
     * 打开日志文件并写入日志
     *
     * @param myLogType type
     * @param info      className,methodName,lineNumber
     * @param text      msg
     */
    private static void writeLogToFile(String myLogType, String[] info, String text) {// 新建或打开日志文件
        if(FILE_NUMBERS<1){
            FILE_NUMBERS=DEFAULT_FILE_NUMBERS;
        }
        if(FILES_SIZE<1){
            FILES_SIZE=DEFAULT_FILES_SIZE;
        }
        String className = info[0];
        String methodName = info[1];
        String lineNumber = info[2];
        Date nowTime = new Date();
        StringBuilder log = new StringBuilder()
                .append(myLogSdf.format(nowTime))
                .append(" [").append(myLogType.toUpperCase()).append("] ")
                .append(tag1+": ")
                .append("[ ")
                .append("(")
                .append(className)
                .append(":")
                .append(lineNumber)
                .append(")")
                .append("#")
                .append(methodName)
                .append(" ]")
                .append(text);

        File localFile = new File(MY_LOG_PATH, "LocalLog");
        if (!localFile.exists()) {
            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
            localFile.mkdirs();
        }
        if (getFils() == null) {
            if(FILE_NAME==null){
                FILE_NAME="MyLog_";
            }
            String fileName =FILE_NAME+getRandomNumber() + ".log";
            File file = new File(localFile, fileName);
            writeDate(file, log);
        } else {
            File[] files = getFils();
            for (int i = 0; i < files.length; i++) {
                if (files[i].length() < FILES_SIZE) {
                    isRequireNewFile = false;
                    File file1 = files[i];
                    writeDate(file1, log);
                    break;
                } else {
                    isRequireNewFile = true;
                }
            }
            if (isRequireNewFile && getFils().length < FILE_NUMBERS) {
                if(FILE_NAME==null){
                    FILE_NAME="MyLog_";
                }
                String fileName =FILE_NAME+getRandomNumber() + ".log";
                File file = new File(localFile, fileName);
                writeDate(file, log);
            } else if (isRequireNewFile && getFils().length >= FILE_NUMBERS) {
                getFils()[0].delete();
                if(FILE_NAME==null){
                    FILE_NAME="MyLog_";
                }
                String fileName = FILE_NAME+getRandomNumber() + ".log";
                File file = new File(localFile, fileName);
                writeDate(file, log);
            }
        }

    }

    private static void writeDate(File file, StringBuilder log) {
        try {
            FileWriter filerWriter = new FileWriter(file, true);//后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(log.toString());
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File[] getFils() {
        File file = new File(MY_LOG_PATH + "/LocalLog");
        File[] files = file.listFiles();
        //文件按修改日期排序
        if (files != null) {
            Arrays.sort(files, new Comparator<File>() {
                public int compare(File f1, File f2) {
                    long diff = f1.lastModified() - f2.lastModified();
                    if (diff > 0)
                        return 1;
                    else if (diff == 0)
                        return 0;
                    else
                        return -1;//设置排序为递增的，如果 if 中修改为 返回-1 同时此处修改为返回 1  排序就会是递减
                }

                public boolean equals(Object obj) {
                    return true;
                }

            });
        }
        return files;
    }

    //生成12位随机数作为文件名后缀
    public static String getRandomNumber() {
        String s = UUID.randomUUID().toString();
        s = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
        return s.substring(0, 12);
    }

    public static String getFilePath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {
            //如果外部储存可用
            return context.getExternalFilesDir(null).getPath();
            //返回外部存储路径,默认路径为 /mnt/sdcard/Android/data/com.wohome.im/files
        } else {
            return context.getFilesDir().getPath();//直接存在/data/data里，非root手机是看不到的
            //返回/data/data/com.wohome.im/files
        }
    }
}
