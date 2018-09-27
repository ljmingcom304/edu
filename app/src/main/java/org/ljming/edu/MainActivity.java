package org.ljming.edu;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.ljming.edu.adapter.MainAdapter;

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

    @Override
    public void initView() {
        mBtnClass = (Button) findViewById(R.id.btn_class);
        mRvItem = (RecyclerView) findViewById(R.id.rv_item);
        mRvItem.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @Override
    public void initDate() {
        mRvItem.setAdapter(new MainAdapter(this));
    }

    @Override
    public void setListener() {
        mBtnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SourceActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
}
