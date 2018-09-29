package org.ljming.edu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ljming.edu.bean.ACacheBean;
import org.ljming.edu.dialog.MenuDialog;
import org.ljming.edu.fragment.ImageFragment;
import org.ljming.edu.fragment.MainFragment;

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


    private Button mBtn01;  //word1
    private Button mBtn02;  //word2
    private Button mBtn03;  //word3
    private Button mBtn04;  //图片
    private Button mBtn05;  //返回
    private TextView mTvPath;
    private MainFragment mainFragment;
    private ImageFragment imageFragment;
    private MenuDialog menuDialog;
    private File unitFile;

    @Override
    public void initView() {
        mBtn01 = (Button) findViewById(R.id.btn_01);
        mBtn02 = (Button) findViewById(R.id.btn_02);
        mBtn03 = (Button) findViewById(R.id.btn_03);
        mBtn04 = (Button) findViewById(R.id.btn_04);
        mBtn05 = (Button) findViewById(R.id.btn_05);    //关闭对话框
        mTvPath = (TextView) findViewById(R.id.tv_path);
    }

    @Override
    public void initDate() {
        mainFragment = new MainFragment();
        imageFragment = new ImageFragment();
        openFragment(mainFragment);

        ACache aCache = ACache.build(this);
        ACacheBean cacheBean = aCache.getAsObject("BEAN", ACacheBean.class);
        if (cacheBean != null) {
            unitFile = cacheBean.unitFile;
            setPath(unitFile);
        }
    }

    public void setPath(File file) {
        if (file != null) {
            String root = FileUtils.getRootFile().getAbsolutePath();
            String path = unitFile.getAbsolutePath().replace(root, "");
            mTvPath.setText(path);
        }
    }

    public void openFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fr_layout, fragment);
        transaction.commit();
    }

    @Override
    public void setListener() {
        menuDialog = new MenuDialog();
        menuDialog.setOnClickListener(new MenuDialog.OnClickListener() {
            @Override
            public void onClick(File file) {
                unitFile = file;
                setPath(unitFile);
                openImage(unitFile);
            }
        });
        mainFragment.setOnClickListener(new MainFragment.OnClickListener() {
            @Override
            public void onClick() {
                menuDialog.show(MainActivity.this);
            }
        });
        mBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWord(unitFile, 0);
            }
        });
        mBtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWord(unitFile, 1);
            }
        });
        mBtn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWord(unitFile, 2);
            }
        });
        mBtn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage(unitFile);
            }
        });
        mBtn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(mainFragment);
            }
        });
    }

    public void openImage(File unitFile) {
        if (unitFile == null) {
            ToastUtils.show(MainActivity.this, "请选择一个单元目录");
        } else {
            ArrayList<String> imageFile = FileUtils.getImageFile(unitFile);
            if (imageFile.size() > 0) {
                imageFragment.setImages(imageFile);
                openFragment(imageFragment);
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
                boolean result = WPSUtils.openFile(MainActivity.this, file.getAbsolutePath());
                if (!result) {
                    ToastUtils.show(MainActivity.this, "Word文件打开失败，请检查WPS是否正确安装");
                }
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
