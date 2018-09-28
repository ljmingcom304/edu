package org.ljming.edu.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;

import org.ljming.edu.R;

import java.util.List;

/**
 * Title:ImageAdapter
 * <p>
 * Description:
 * </p>
 * Author Jming.L
 * Date 2018/9/28 16:19
 */
public class ImageAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mList;

    public ImageAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.adapter_image,null);
        String url = mList.get(position);
        PhotoView pvImage= (PhotoView) view.findViewById(R.id.pv_image);
        pvImage.enable();           //开启缩放
        pvImage.setMaxScale(5.0f);  //缩放倍数
        pvImage.setImageURI(Uri.parse(url));
        return view;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
