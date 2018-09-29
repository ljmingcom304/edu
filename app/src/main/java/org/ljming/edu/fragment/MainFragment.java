package org.ljming.edu.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.ljming.edu.BaseFragment;
import org.ljming.edu.R;
import org.ljming.edu.adapter.MainAdapter;

/**
 * Title:MainFragment
 * <p>
 * Description:主页面
 * </p>
 * Author Jming.L
 * Date 2018/9/29 13:43
 */
public class MainFragment extends BaseFragment {

    private Button mBtnClass;
    private RecyclerView mRvItem;
    private OnClickListener listener;

    public interface OnClickListener {
        void onClick();
    }

    @Override
    protected void initView(View view) {
        mBtnClass = (Button) view.findViewById(R.id.btn_class);
        mRvItem = (RecyclerView) view.findViewById(R.id.rv_item);
        mRvItem.setLayoutManager(new GridLayoutManager(getActivity(), 4));
    }

    @Override
    protected void initData() {
        mRvItem.setAdapter(new MainAdapter(getActivity()));
        mBtnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick();
                }
            }
        });
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }
}
