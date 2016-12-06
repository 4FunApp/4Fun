package com.joker.fourfun.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.joker.fourfun.ui.fragment.PictureChildFragment;

import java.util.List;

/**
 * Created by joker on 2016/12/4.
 */

public class PicOneAdapter extends FragmentStatePagerAdapter {
    private List<PictureChildFragment> mFragments;

    public PicOneAdapter(FragmentManager fm, List<PictureChildFragment> fragmentList) {
        super(fm);
        mFragments = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
