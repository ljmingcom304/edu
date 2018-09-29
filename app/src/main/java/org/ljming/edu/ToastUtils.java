package org.ljming.edu;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Title: ToastUtils
 * <p>
 * Description:
 * </p>
 * Author: Jming.L
 * Date: 2018/9/27 20:39
 * Copyright: Jming.Liang All rights reserved.
 */
public class ToastUtils {

    public static void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

}
