package org.ljming.edu;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.Window;

/**
 * Title: BaseActivity
 * <p>
 * Description:基类
 * </p>
 * Author: Jming.L
 * Date: 2018/9/24 21:42
 * Copyright: Jming.Liang All rights reserved.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        initView();
        initDate();
        setListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setListener() {

    }

    public abstract void initView();

    public abstract void initDate();

    public abstract int getLayout();


}