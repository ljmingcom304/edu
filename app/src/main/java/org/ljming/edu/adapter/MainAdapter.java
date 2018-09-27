package org.ljming.edu.adapter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ljming.edu.R;
import org.ljming.edu.bean.SourceBean;

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
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private Activity mActivity;
    private List<SourceBean> mList;
    public static final String FILE_SETTING = "设置";
    public static final String FILE_FILE = "文件管理";
    public static final String FILE_IMAGE = "图库";
    public static final String FILE_PHOTO = "相机";
    public static final String FILE_CONTANT = "联系人";
    public static final String FILE_PHONE = "拨号";
    public static final String FILE_COMPUTER = "计算机";
    public static final String FILE_RECORDER = "录音机";
    public static final String FILE_TODO = "待办事项";
    public static final String FILE_CLOCK = "时钟";
    public static final String FILE_CALENDAR = "日历";


    public MainAdapter(Activity activity) {
        this.mActivity = activity;
        mList = new ArrayList<>();
        mList.add(new SourceBean(FILE_SETTING, R.drawable.icon_shezhi));
        mList.add(new SourceBean(FILE_FILE, R.drawable.icon_wenjian));
        mList.add(new SourceBean(FILE_IMAGE, R.drawable.icon_tuku));
        mList.add(new SourceBean(FILE_PHOTO, R.drawable.icon_xiangji));
        mList.add(new SourceBean(FILE_CONTANT, R.drawable.icon_lianxiren));
        mList.add(new SourceBean(FILE_PHONE, R.drawable.icon_bohao));
        mList.add(new SourceBean(FILE_COMPUTER, R.drawable.icon_jisuanji));
        mList.add(new SourceBean(FILE_RECORDER, R.drawable.icon_luyinji));
        mList.add(new SourceBean(FILE_TODO, R.drawable.icon_daiban));
        mList.add(new SourceBean(FILE_CLOCK, R.drawable.icon_shizhong));
        mList.add(new SourceBean(FILE_CALENDAR, R.drawable.icon_rili));
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mActivity, R.layout.adapter_main, null);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, final int position) {
        final SourceBean bean = mList.get(position);
        holder.mTvItem.setText(bean.name);
        holder.mIvIcon.setImageResource(bean.resId);
        holder.mLlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open(bean);
            }
        });
    }

    private void open(SourceBean bean) {
        //系统设置
        if (TextUtils.equals(bean.name, FILE_SETTING)) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            mActivity.startActivity(intent);
        }
        //文件管理器
        if (TextUtils.equals(bean.name, FILE_FILE)) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            mActivity.startActivityForResult(intent, 1);
        }
        //打开系统图库
        if (TextUtils.equals(bean.name, FILE_IMAGE)) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            mActivity.startActivityForResult(intent, 2);
        }
        //打开系统相机
        if (TextUtils.equals(bean.name, FILE_PHOTO)) {
            File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            Uri imageUri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
            mActivity.startActivityForResult(intent, 3);
        }
        //打开联系人
        if (TextUtils.equals(bean.name, FILE_CONTANT)) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);//vnd.android.cursor.dir/contact
            mActivity.startActivityForResult(intent, 4);
        }
        //打开拨号界面
        if (TextUtils.equals(bean.name, FILE_PHONE)) {
            Intent intent = new Intent(Intent.ACTION_CALL_BUTTON);//跳转到拨号界面
            mActivity.startActivity(intent);
        }
        //打开系统计算器
        if (TextUtils.equals(bean.name, FILE_COMPUTER)) {
            Intent intent = new Intent();
            intent.setClassName("com.android.calculator2",
                    "com.android.calculator2.Calculator");
            mActivity.startActivity(intent);
        }
        //打开录音机
        if (TextUtils.equals(bean.name, FILE_RECORDER)) {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            mActivity.startActivity(intent);
        }
        //打开系统代办
        if (TextUtils.equals(bean.name, FILE_TODO)) {

        }
        //打开系统闹钟
        if (TextUtils.equals(bean.name, FILE_CLOCK)) {
            Intent alarms = new Intent(AlarmClock.ACTION_SET_ALARM);
            mActivity.startActivity(alarms);
        }
        //打开系统日历
        if (TextUtils.equals(bean.name, FILE_CALENDAR)) {
            Intent intent = new Intent();
            ComponentName cn = null;
            if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
                cn = new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity");
            } else {
                cn = new ComponentName("com.google.android.calendar", "com.android.calendar.LaunchActivity");
            }
            intent.setComponent(cn);
            mActivity.startActivity(intent);
        }
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
