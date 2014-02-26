package com.example.app;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by knxy on 1/15/14.
 */
public class ImgAdapter extends PagerAdapter {

    private final Context mContext;
    private float pageWidth;
    private static final int[] img = {
            android.R.drawable.ic_menu_compass,
            android.R.drawable.ic_menu_add,
            android.R.drawable.ic_menu_upload_you_tube,
            android.R.drawable.ic_menu_camera,
    };

    public ImgAdapter(Context context, float pageWidth) {
        super();
        mContext = context;
        this.pageWidth = pageWidth;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view == o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(mContext);
        iv.setImageResource(img[position]);

        container.addView(iv);

        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super destroyItem was not be to called or else cause exception!
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return this.pageWidth;
    }
}
