package org.ljming.edu;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Title: BaseActivity
 * <p>
 * Description:基类
 * </p>
 * Author: Jming.L
 * Date: 2018/9/24 21:42
 * Copyright: Jming.Liang All rights reserved.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(getLayout());
        showReturn(true);
        initView();
        initDate();
        setListener();
    }

    protected void showReturn(boolean show) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(show);
            actionBar.setDisplayHomeAsUpEnabled(show);
        }
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