package com.joker.fourfun.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joker.fourfun.R;
import com.joker.fourfun.adapter.PicOneAdapter;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.presenter.PicturePresenter;
import com.joker.fourfun.presenter.contract.PictureContract;
import com.joker.fourfun.utils.SystemUtil;
import com.joker.fourfun.view.ZoomOutPageTransformer;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureFragment extends BaseMvpFragment<PictureContract.View, PicturePresenter> implements
        PictureContract.View {
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    private PicOneAdapter mAdapter;
    private List<PictureChildFragment> mFragments;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        ButterKnife.bind(this, view);
        mVpContent.setOffscreenPageLimit(1);
        mVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                mPresenter.getContent((position + 1) * -1);
                Logger.i(position + "selected", position + "selected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mFragments = new ArrayList<PictureChildFragment>();
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mVpContent.setPageTransformer(true, new ZoomOutPageTransformer());
        mVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                Logger.e("the " + position + " page is selected", "");
                mFragments.get(position).setFragmentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mAdapter = new PicOneAdapter(getChildFragmentManager(), mFragments);
        mVpContent.setOffscreenPageLimit(1);
        mVpContent.setAdapter(mAdapter);

        return view;
    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }

    @Override
    public void showError(String message) {
        SystemUtil.showToast(mActivity, message);
    }

    public static PictureFragment newInstance() {
        return null;
    }
}
