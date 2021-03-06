package org.ljming.edu;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.ljming.edu.adapter.MenuAdapter;
import org.ljming.edu.bean.MenuBean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: SourceActivity
 * <p>
 * Description:我的课程
 * </p>
 * Author: Jming.L
 * Date: 2018/9/24 21:41
 * Copyright: Jming.Liang All rights reserved.
 */
public class SourceActivity extends BaseActivity {

    public static final int ROOT_ID = -1;//根ID
    private RecyclerView mRvList;
    private MenuAdapter mAdapter;
    private List<MenuBean> mBeans;
    private SharedPreferences mShared;

    @Override
    public void initView() {
        mRvList = (RecyclerView) findViewById(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MenuAdapter(this);
        mRvList.setAdapter(mAdapter);
    }

    @SuppressWarnings( "ResultOfMethodCallIgnored" )
    @Override
    public void initDate() {
        mBeans = new ArrayList<>();
        //获取根目录下目录
        Map<String, File> mFileMap = new HashMap<>();
        File rootFile = FileUtils.getRootFile();
        File[] rootFiles = rootFile.listFiles();
        if (rootFiles != null) {
            for (File file : rootFiles) {
                if (file.isDirectory()) {
                    mFileMap.put(file.getName(), file);
                }
            }
        }

        //遍历课程判断目录是否存在若不存在则创建目录
        String[] sources = {"语文", "数学", "英语", "物理", "化学", "生物", "政治", "历史", "地理"};
        for (int i = 0; i < sources.length; i++) {
            String name = sources[i];
            File file = null;
            if (!mFileMap.containsKey(name)) {
                file = new File(rootFile, name);
                file.mkdir();
            } else {
                file = mFileMap.get(name);
            }
            MenuBean menuBean = new MenuBean(mBeans.size() + 1, ROOT_ID, file, 0, false);
            mBeans.add(menuBean);
        }

        //加载课程目录下的年级目录
        loadMenu(0);
        //加载年级目录下的单元目录
        loadMenu(1);
        //加载单元目录下所有的试卷目录
        loadMenu(2);
        mAdapter.setItem(getExpendBeans(ROOT_ID, true));
    }

    /**
     * 获取需要展示的父级ID
     */
    private List<Integer> getShowParentMenuID(int parentId) {
        ArrayList<Integer> list = new ArrayList<>();
        if (parentId > ROOT_ID) {
            return list;
        }
        for (int i = 0; i < mBeans.size(); i++) {
            MenuBean menuBean = mBeans.get(i);
            if (parentId == menuBean.id) {
                list.add(menuBean.id);
                list.addAll(getShowParentMenuID(menuBean.parentId));
                break;
            }
        }
        return list;
    }

    /**
     * 获取展开后的JavaBean
     *
     * @param parentId 要展开的菜单ID
     * @param isExpend 是否展开还是关闭
     */
    private List<MenuBean> getExpendBeans(int parentId, boolean isExpend) {
        //隐藏树结构的其他分支
        List<Integer> showList = getShowParentMenuID(parentId);
        for (int i = 0; i < mBeans.size(); i++) {
            MenuBean menuBean = mBeans.get(i);
            if (showList.contains(menuBean.id)) {
                //在ID列表中既显示又扩展
                menuBean.isShow = true;
                menuBean.isExpend = true;
            } else {
                //父ID非根目录不显示不扩展
                if (menuBean.parentId != ROOT_ID) {
                    menuBean.isShow = false;
                    menuBean.isExpend = false;
                } else {
                    //父ID为根目录显示不扩展
                    menuBean.isShow = true;
                    if (parentId == menuBean.id && isExpend) {
                        menuBean.isExpend = true;
                    } else {
                        menuBean.isExpend = false;
                    }
                }
            }
        }


        //获取父ID，展开所有父ID下所有的子菜单
        for (int i = 0; i < mBeans.size(); i++) {
            MenuBean menuBean = mBeans.get(i);
            if (menuBean.parentId == parentId) {
                menuBean.isShow = isExpend;
            }
        }

        //如果菜单是显示的则添加到集合中
        List<MenuBean> list = new ArrayList<>();
        for (int i = 0; i < mBeans.size(); i++) {
            MenuBean menuBean = mBeans.get(i);
            if (menuBean.isShow) {
                list.add(menuBean);
            }
        }
        return list;
    }

    /**
     * 0课程目录1年级目录2单元目录3试卷目录
     *
     * @param level 父级目录级别
     */
    private void loadMenu(int level) {
        List<MenuBean> temp = new ArrayList<>();
        for (MenuBean bean : mBeans) {
            if (bean.level == level) {
                File file = bean.file;
                if (file != null) {
                    //课程单元
                    File[] childFiles = file.listFiles();
                    for (int i = 0; childFiles != null && i < childFiles.length; i++) {
                        File unitFile = childFiles[i];
                        if (unitFile.isDirectory()) {
                            MenuBean menuBean = new MenuBean(mBeans.size() + temp.size() + 1, bean.id, unitFile, bean.level + 1, false);
                            temp.add(menuBean);
                        }
                    }
                }
            }
        }
        mBeans.addAll(temp);
    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MenuBean bean) {
                mAdapter.setItem(getExpendBeans(bean.id, bean.isExpend));
            }
        });
    }

    /**
     * 获取图片文件
     */
    public List<String> getImageFile(File file) {
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
    public List<File> getWordFile(File file) {
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

    @Override
    public int getLayout() {
        return R.layout.activity_source;
    }
}
