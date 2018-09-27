package org.ljming.edu.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ljming.edu.R;
import org.ljming.edu.SourceActivity;
import org.ljming.edu.bean.MenuBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: MainAdapter
 * <p>
 * Description:
 * </p>
 * Author: Jming.L
 * Date: 2018/9/24 20:29
 * Copyright: Jming.Liang All rights reserved.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MainHolder> {

    private Activity mActivity;
    private List<MenuBean> mBeans;
    private OnItemClickListener mListener;
    private int size30;

    public interface OnItemClickListener {
        void onItemClick(MenuBean bean);
    }

    public MenuAdapter(Activity activity) {
        this.mActivity = activity;
        this.size30 = activity.getResources().getDimensionPixelSize(R.dimen.size_30);
        mBeans = new ArrayList<>();
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mActivity, R.layout.adapter_menu, null);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, final int position) {
        final MenuBean bean = mBeans.get(position);
        File file = bean.file;
        holder.ivIcon.setSelected(bean.isExpend);
        holder.tvItem.setText(file.getName());
        holder.llMenu.setPadding(size30 * bean.level, 0, 0, 0);
        holder.llMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    bean.isExpend = !bean.isExpend;
                    mListener.onItemClick(bean);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void setItem(List<MenuBean> beans) {
        this.mBeans = getChildMenu(SourceActivity.ROOT_ID, beans);
        notifyDataSetChanged();
    }

    /**
     * 递归遍历
     */
    private List<MenuBean> getChildMenu(int parentId, List<MenuBean> beans) {
        List<MenuBean> list = new ArrayList<>();
        for (int i = 0; beans != null && i < beans.size(); i++) {
            MenuBean menuBean = beans.get(i);
            if (menuBean.parentId == parentId) {
                list.add(menuBean);
                List<MenuBean> childMenu = getChildMenu(menuBean.id, beans);
                list.addAll(childMenu);
            }
        }
        return list;
    }


    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder {

        private LinearLayout llMenu;
        private ImageView ivIcon;
        private TextView tvItem;

        public MainHolder(View itemView) {
            super(itemView);
            llMenu = (LinearLayout) itemView.findViewById(R.id.ll_menu);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

}
