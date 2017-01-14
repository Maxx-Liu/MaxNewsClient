package com.max.news.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.max.news.base.BaseActivity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity的管理和应用程序退出
 *
 * @auther MaxLiu
 * @time 2017/1/14
 */

public class AppManager {
    private static Stack<BaseActivity> activityStack;
    private static AppManager instance;

    private AppManager(){

    }

    /**
     * 单实例,UI无需考虑多线程同步问题
     * @return AppManager单实例
     */
    public static AppManager getAppManager(){
        if(instance == null){
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到Stack
     * @param activity activity实例
     */
    public void addActivity(BaseActivity activity){
        if(activityStack == null){
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity(栈顶Activity)
     * @return 当前的Activity
     */
    public BaseActivity currentActivity(){
        if(activityStack == null || activityStack.isEmpty()){
            return null;
        }
        return activityStack.lastElement();
    }

    /**
     * 获取指定Activity
     * @reutrn 指定的Activity实例
     */
    public BaseActivity findActivity(Class<?> cls){
        BaseActivity activity = null;
        for (BaseActivity aty: activityStack) {
            if(aty.getClass().equals(cls)){
                activity = aty;
                break;
            }
        }
        return activity;
    }

    /**
     * 结束当前Activity(栈顶)
     */
    public void finishCurrentActivity(){
        BaseActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     * @param activity 指定的Activity
     */
    public void finishActivity(Activity activity){
        if(activity != null){
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定的Activity
     * @param cls 指定的Activity的class对象
     */
    public void finishActivity(Class<?> cls){
        for (BaseActivity activity:
             activityStack) {
            if(activity.getClass().equals(cls)){
                finishActivity(cls);
            }
        }
    }

    /**
     * 关闭除指定Activity的所有Activity
     * @param cls 指定Activity的class对象
     */
    public void finishOtherActivity(Class<?> cls){
        for(BaseActivity activity : activityStack){
            if(!activity.getClass().equals(cls)){
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 应用程序退出
     * @param context 当前上下文
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }
}
