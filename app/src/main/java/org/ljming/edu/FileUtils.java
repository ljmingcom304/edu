package org.ljming.edu;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: FileUtils
 * <p>
 * Description:
 * </p>
 * Author: Jming.L
 * Date: 2018/9/24 22:34
 * Copyright: Jming.Liang All rights reserved.
 */
public class FileUtils {

    public static final int FILE_TXT = 0;
    public static final int FILE_WORD = 1;
    public static final int FILE_IMAGE = 2;

    /**
     * 获取课程所在根路径
     */
    public static File getRootFile() {
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard, "BCY001");
            if (!file.isDirectory() || !file.exists()) {
                file.mkdir();
            }
            for (File file1 : sdcard.listFiles()) {
                Log.i("TAG", file1.getAbsolutePath());
            }
            Log.i("TAG", file.getAbsolutePath());
            return file;
        }
        return null;
    }

    public static int getFileType(File file) {
        String name = file.getName().toLowerCase();

        if (name.endsWith(".docx") || name.equals(".doc")) {
            return FILE_WORD;
        }
        String[] images = {".png", ".bmp", "jpg", ".gif", ".jpeg"};
        for (String image : images) {
            if (name.endsWith(image)) {
                return FILE_IMAGE;
            }
        }
        return FILE_TXT;
    }

    /**
     * 获取图片文件
     */
    public static List<String> getImageFile(File file) {
        File[] files = file.listFiles();
        List<String> list = new ArrayList<>();
        for (int i = 0; files != null && i < files.length; i++) {
            File f = files[i];
            if (FileUtils.getFileType(f) == FileUtils.FILE_IMAGE) {
                list.add(f.getAbsolutePath());
            }
        }
        return list;
    }

    /**
     * 获取Word文件
     */
    public static List<File> getWordFile(File file) {
        File[] files = file.listFiles();
        List<File> list = new ArrayList<>();
        for (int i = 0; files != null && i < files.length; i++) {
            File f = files[i];
            if (FileUtils.getFileType(f) == FileUtils.FILE_IMAGE) {
                list.add(f);
            }
        }
        return list;
    }

}
