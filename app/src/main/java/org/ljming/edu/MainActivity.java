package org.ljming.edu;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.ljming.edu.adapter.MainAdapter;
import org.ljming.edu.dialog.MenuDialog;

/**
 * Title: MainActivity
 * <p>
 * Description:
 * </p>
 * Author: Jming.L
 * Date: 2018/9/24 20:28
 * Copyright: Jming.Liang All rights reserved.
 */
public class MainActivity extends BaseActivity {

    private Button mBtnClass;
    private RecyclerView mRvItem;
    private Button mBtn01;
    private Button mBtn02;
    private Button mBtn03;
    private Button mBtn04;
    private Button mBtn05;

    @Override
    public void initView() {
        mBtnClass = (Button) findViewById(R.id.btn_class);
        mRvItem = (RecyclerView) findViewById(R.id.rv_item);
        mRvItem.setLayoutManager(new GridLayoutManager(this, 4));
        mBtn01 = (Button) findViewById(R.id.btn_01);
        mBtn02 = (Button) findViewById(R.id.btn_02);
        mBtn03 = (Button) findViewById(R.id.btn_03);
        mBtn04 = (Button) findViewById(R.id.btn_04);
        mBtn05 = (Button) findViewById(R.id.btn_05);    //关闭对话框
    }

    @Override
    public void initDate() {
        mRvItem.setAdapter(new MainAdapter(this));
    }

    @Override
    public void setListener() {
        final MenuDialog menuDialog = new MenuDialog();
        mBtnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDialog.show(MainActivity.this);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
}
