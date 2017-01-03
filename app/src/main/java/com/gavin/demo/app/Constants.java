package com.gavin.demo.app;

import android.os.Environment;

import java.io.File;

/**
 * 常量类
 */
public class Constants {


    /**
     * 常规参数
     */
    public static final class General {

        public static final String IMAGE_END = ".jpg";
        public static final String UTF8 = "UTF-8";
        public static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
        public static final String DATEFORMAT2 = "yyyyMMddHHmmss";
        public static final String DB_NAME = "gavin.db";
        public static final int PAGE_SIZE = 15;
        public static final int PAGE_SIZE_SHOW = 8;
        /**
         * 导航页跳转MainFragment
         */
        public static final int NAVIGATION_JUMP_TYPE_MAIN = 1;
        /**
         * 导航页跳转SelectCityFragment
         */
        public static final int NAVIGATION_JUMP_TYPE_CITY = 2;
        /**
         * 设置页面跳转到引导页
         */
        public static final int NAVIGATION_JUMP_TYPE_GUIDE = 3;
        /**
         * 默认最小软键盘高度
         */
        public static final int DEFAULT_SOFT_KEY_BOARD_MIN_HEIGHT = 100;
    }

    /**
     * intent 携带参数key
     */
    public static final class IntentExtra {
        public static final String IS_OPEN_AGAIN = "isOpenAgain";
        public static final String CHECK_VERSION_TIME = "checkVersionTime";
        public static final String ENTER_STATUS_ENABLE = "enterStatusEnable";
        /**
         * 用户ID
         */
        public static final String USER_ID = "userId";
        /**
         * 用户
         */
        public static final String USER = "user";
    }

    /**
     * 请求码
     */
    public static final class RequestCode {
        /**
         * 拍照
         */
        public static final int TAKE_PHOTO = 0X101;
        /**
         * 圖庫
         */
        public static final int GALLERY = 0x102;
        /**
         * 裁剪
         */
        public static final int ZOOM = 0x103;
        /**
         * 请求必要的权限
         */
        public static final int PERMISSION_NEEDFUL = 0x104;
        /**
         * 去设置页更改权限
         */
        public static final int PERMISSION_SETTING = 0x105;
        /**
         * 扫描
         */
        public static final int SCAN = 0x106;

    }

    /**
     * 请求Action
     */
    public static final class Action {
        /**
         * MainFragment准备就绪
         */
        public static final String MAIN_FRAGMENT_READY = "com.tn.omg.main.APP_READY";
        /**
         * 登录状态改变
         */
        public static final String LOGIN_STATUS_CHANGE = "com.tn.omg.user.LOGIN_CHANGE";
        /**
         * 用户信息修改
         */
        public static final String USER_INFO_CHANGE = "com.tn.omg.user.INFO_CHANGE";
    }

    public static class SDCard {
        /**
         * SDCARD根目录
         */
        public final static String SDCARD = Environment.getExternalStorageDirectory().getPath();
        /**
         * 项目文件夹
         */
        private static final String STORAGE_DIR = SDCARD + File.separatorChar + "omg" + File.separatorChar;
        /**
         * 图片缓存文件夹
         */
        public final static String CACHE = "cache" + File.separatorChar;

        public static String getCacheDir() {
            File file = Environment.getExternalStorageDirectory();
            if (file.canWrite()) {
                file = new File(STORAGE_DIR + CACHE);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            return STORAGE_DIR + CACHE;
        }
    }
}
