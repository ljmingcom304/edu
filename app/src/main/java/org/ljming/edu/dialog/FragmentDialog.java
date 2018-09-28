package org.ljming.edu.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Title:FragmentDialog
 * <p>
 * Description:Fragment弹窗
 * </p>
 * Author Jming.L
 * Date 2017/10/19 16:02
 */
public abstract class FragmentDialog extends DialogFragment implements LayoutDialog {

    private int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    private Window mWindow;
    private OnCloseListener mListener;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initWindow();
        View view = onCreateView(inflater, container);
        return view;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(setLayout(), container, false);
        view.setMinimumWidth(mWidth);
        view.setMinimumHeight(mHeight);
        initView(view);
        initData();
        return view;
    }

    public void initWindow() {
        mWindow = getDialog().getWindow();
        if (mWindow != null) {
            mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            mWindow.requestFeature(Window.FEATURE_NO_TITLE);
            mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mWindow.setLayout(mWidth, mHeight);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.onClose();
        }
    }

    public Window getWindow() {
        return mWindow;
    }

    @Override
    public Context getLayoutContext(){
        return getActivity();
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public void show(Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(this);
        transaction.commitAllowingStateLoss();
        super.show(manager, this.getTag());
    }

    @Override
    public void setOnCloseListener(OnCloseListener listener) {
        mListener = listener;
    }

    @Override
    public void dismiss() {
        //解决Activity状态无法保存崩溃的异常
        super.dismissAllowingStateLoss();
    }
}
