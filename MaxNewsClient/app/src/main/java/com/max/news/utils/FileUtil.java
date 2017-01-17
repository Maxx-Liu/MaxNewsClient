package com.max.news.utils;

import android.os.Environment;
import android.util.Log;

import com.max.news.application.NewsApplication;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具包
 *
 * @auther MaxLiu
 * @time 2017/1/14
 */

public class FileUtil {
    /**
     * 检测SD卡是否存在
     */
    public static boolean checkSDcard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    /**
     * 获取文件保存点
     */
    public static File getSaveFile(String fileNmae) {
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory()
                    .getCanonicalFile() + "/" + fileNmae);
        } catch (IOException e) {
        }
        return file;
    }

    /**
     * 从指定文件夹获取文件
     */
    public static File getSaveFile(String folder, String fileNmae) {
        File file = new File(getSavePath(folder), fileNmae);
        return file;
    }

    /**
     * 获取文件保存路径
     */
    public static String getSavePath(String folder) {
        return Environment.getExternalStorageDirectory() + "/" + folder;
    }

    /**
     * @return  创建缓存目录
     */
    public static File getCacheDirectory()
    {
        File file = new File(NewsApplication.getInstance().getApplicationContext().getExternalCacheDir(), "MyCache");
        if(!file.exists())
        {
            boolean b = file.mkdirs();
            Log.e("file", "文件不存在  创建文件    "+b);
        }else{
            Log.e("file", "文件存在");
        }
        return file;
    }
}
