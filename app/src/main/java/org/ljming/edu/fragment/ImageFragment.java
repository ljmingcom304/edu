package org.ljming.edu.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import org.ljming.edu.BaseFragment;
import org.ljming.edu.R;
import org.ljming.edu.adapter.ImageAdapter;

import java.util.List;

/**
 * Title:ImageFragment
 * <p>
 * Description:图片显示
 * </p>
 * Author Jming.L
 * Date 2018/9/29 13:42
 */
public class ImageFragment extends BaseFragment {

    public static final String FILE = "unitFile";
    private ViewPager mVpImage;     //照片
    private TextView mTvCount;      //页码
    private ImageAdapter mImageAdapter;
    private List<String> urls;

    @Override
    public void initView(View view) {
        mVpImage = (ViewPager) view.findViewById(R.id.vp_image);
        mTvCount = (TextView) view.findViewById(R.id.tv_count);
        mVpImage.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTvCount.setText("第" + (position + 1) + "页（共" + mImageAdapter.getCount() + "页）");
            }
        });
        mImageAdapter = new ImageAdapter(getActivity());
        mVpImage.setAdapter(mImageAdapter);
        mImageAdapter.setItems(urls);
        mVpImage.setCurrentItem(0);
    }

    @Override
    protected void initData() {

    }

    public void setImages(List<String> urls) {
        this.urls = urls;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_image;
    }

}
