package org.ljming.edu;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import org.ljming.edu.adapter.ImageAdapter;

import java.util.ArrayList;

/**
 * Title:ImageActivity
 * <p>
 * Description:图片
 * </p>
 * Author Jming.L
 * Date 2018/9/28 16:06
 */
public class ImageActivity extends BaseActivity {

    public static final String FILE = "file";
    private ViewPager mVpImage;     //照片
    private TextView mTvCount;      //页码
    private ImageAdapter mImageAdapter;

    @Override
    public void initView() {
        mVpImage = (ViewPager) findViewById(R.id.vp_image);
        mTvCount = (TextView) findViewById(R.id.tv_count);
    }

    @Override
    public void initDate() {
        Intent intent = getIntent();
        ArrayList<String> urls = intent.getStringArrayListExtra(FILE);
        mImageAdapter = new ImageAdapter(this, urls);
        mVpImage.setAdapter(mImageAdapter);
        mVpImage.setCurrentItem(0);
    }

    @Override
    public void setListener() {
        mVpImage.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTvCount.setText("第" + position + "页（共" + mImageAdapter.getCount() + "页）");
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_image;
    }
}
