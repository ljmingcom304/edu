package org.ljming.edu.dialog;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.ljming.edu.FileUtils;
import org.ljming.edu.R;
import org.ljming.edu.adapter.MenuAdapter;
import org.ljming.edu.bean.MenuBean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title:MenuDialog
 * <p>
 * Description:菜单弹窗
 * </p>
 * Author Jming.L
 * Date 2018/9/28 18:41
 */
public class MenuDialog extends FragmentDialog {


    public static final int ROOT_ID = -1;//根ID
    private RecyclerView mRvList;
    private MenuAdapter mAdapter;
    private List<MenuBean> mBeans;
    private List<MenuBean> mCurBeans;
    private File mUnitFile;         //单元文件

    @Override
    public void initView(View view) {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        Resources resources = getResources();
        this.setWidth(resources.getDimensionPixelOffset(R.dimen.size_800));
        this.setHeight(resources.getDimensionPixelOffset(R.dimen.size_500));
        mRvList = (RecyclerView) view.findViewById(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MenuAdapter(getActivity());
        mRvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MenuBean bean) {
                mCurBeans = getExpendBeans(bean, bean.id, bean.isExpend);
                mAdapter.setItem(mCurBeans);
                if (bean.level == 2 && bean.isExpend) {
                    mUnitFile = bean.file;
                } else {
                    mUnitFile = null;
                }
            }
        });
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void initData() {
        if (mBeans == null) {
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
        }

        if (mCurBeans == null) {
            mCurBeans = getExpendBeans(null, ROOT_ID, true);
            mAdapter.setItem(mCurBeans);
        }
        mAdapter.setItem(mCurBeans);
    }

    /**
     * 获取展开后的JavaBean
     *
     * @param parentId 要展开的菜单ID
     * @param isExpend 是否展开还是关闭
     */
    private List<MenuBean> getExpendBeans(MenuBean item, int parentId, boolean isExpend) {
        //若为单元目录只展开当前单元不展开其他单元
        if (item != null && item.level == 2) {
            for (int i = 0; i < mBeans.size(); i++) {
                MenuBean menuBean = mBeans.get(i);
                if (item != menuBean && menuBean.level == 2) {
                    menuBean.isExpend = false;
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

    /**
     * 获取单元文件，单元File为NULL则不是单元目录，listFiles为空则该文件下没有其他文件
     */
    public File getUnitFile() {
        return mUnitFile;
    }

    @Override
    public int setLayout() {
        return R.layout.dialog_menu;
    }
}
