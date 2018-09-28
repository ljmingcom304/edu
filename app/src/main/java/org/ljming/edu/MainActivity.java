package org.ljming.edu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.ljming.edu.adapter.MainAdapter;
import org.ljming.edu.dialog.MenuDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    private Button mBtn01;  //word1
    private Button mBtn02;  //word2
    private Button mBtn03;  //word3
    private Button mBtn04;  //图片
    private Button mBtn05;  //返回

    @Override
    public void initView() {
        showReturn(false);
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
        mBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File unitFile = menuDialog.getUnitFile();
                openWord(unitFile, 0);
            }
        });
        mBtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File unitFile = menuDialog.getUnitFile();
                openWord(unitFile, 1);
            }
        });
        mBtn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File unitFile = menuDialog.getUnitFile();
                openWord(unitFile, 2);
            }
        });
        mBtn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File unitFile = menuDialog.getUnitFile();
                openImage(unitFile);
            }
        });
    }

    public void openImage(File unitFile) {
        if (unitFile == null) {
            ToastUtils.show(MainActivity.this, "请选择一个单元目录");
        } else {
            ArrayList<String> imageFile = FileUtils.getImageFile(unitFile);
            if (imageFile.size() > 0) {
                Intent intent = new Intent(this, ImageActivity.class);
                intent.putExtra(ImageActivity.FILE, imageFile);
                startActivity(intent);
            } else {
                ToastUtils.show(MainActivity.this, "单元目录下未发现图片文件");
            }
        }
    }

    public void openWord(File unitFile, int position) {
        if (unitFile == null) {
            ToastUtils.show(MainActivity.this, "请选择一个单元目录");
        } else {
            List<File> wordFile = FileUtils.getWordFile(unitFile);
            if (wordFile.size() > position) {
                File file = wordFile.get(position);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri uri = Uri.parse(file.getAbsolutePath());
                //intent.setDataAndType(uri, "application/msword");
                intent.setDataAndType(uri, "*/*");
                startActivity(intent);
            } else {
                ToastUtils.show(MainActivity.this, "单元目录下未发现指定Word文件");
            }
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
}
