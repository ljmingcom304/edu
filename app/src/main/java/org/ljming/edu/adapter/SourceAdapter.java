package org.ljming.edu.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ljming.edu.FileUtils;
import org.ljming.edu.R;

import java.io.File;
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
public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.MainHolder> {

    private Activity mActivity;
    private List<File> mList;


    public SourceAdapter(Activity activity, List<File> list) {
        this.mActivity = activity;
        this.mList = list;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mActivity, R.layout.adapter_source, null);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, final int position) {
        final File file = mList.get(position);
        int type = FileUtils.getFileType(file);
        holder.mTvItem.setText(file.getName());
        if (type == FileUtils.FILE_IMAGE) {
            holder.mIvIcon.setImageResource(R.drawable.icon_type_image);
        } else if (type == FileUtils.FILE_PDF) {
            holder.mIvIcon.setImageResource(R.drawable.icon_type_pdf);
        } else {
            holder.mIvIcon.setImageResource(R.drawable.icon_type_file);
        }
        holder.mLlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder {

        private LinearLayout mLlLayout;
        private ImageView mIvIcon;
        private TextView mTvItem;

        public MainHolder(View itemView) {
            super(itemView);
            mLlLayout = (LinearLayout) itemView.findViewById(R.id.ll_layout);
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            mTvItem = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

}
