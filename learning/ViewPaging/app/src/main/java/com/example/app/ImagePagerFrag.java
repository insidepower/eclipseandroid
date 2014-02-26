package com.example.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by knxy on 1/15/14.
 */
public class ImagePagerFrag extends Fragment {
    private final float pageWidth;
    Context mContext;
    ViewPager viewpager;

    public ImagePagerFrag(float pageWidth) {
        this.pageWidth = pageWidth;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        viewpager = new ViewPager(mContext);
        viewpager.setAdapter(new ImgAdapter(mContext, pageWidth));
        return viewpager;
    }


}
