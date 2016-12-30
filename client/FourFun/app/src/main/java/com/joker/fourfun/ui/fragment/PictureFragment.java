package com.joker.fourfun.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joker.fourfun.Constants;
import com.joker.fourfun.R;
import com.joker.fourfun.adapter.PictureOneAdapter;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.presenter.PicturePresenter;
import com.joker.fourfun.presenter.contract.PictureContract;
import com.joker.fourfun.utils.SystemUtil;
import com.joker.fourfun.widget.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureFragment extends BaseMvpFragment<PictureContract.View, PicturePresenter> implements
        PictureContract.View {
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    private Bundle mPictureBundle;
    private List<PictureChildFragment> mFragments;

    public static PictureFragment newInstance(Bundle bundle) {
        PictureFragment pictureFragment = new PictureFragment();
        pictureFragment.setArguments(bundle);

        return pictureFragment;
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        analyseArguments();
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        ButterKnife.bind(this, view);
        mVpContent.setOffscreenPageLimit(1);
        mFragments = new ArrayList<PictureChildFragment>();
        mFragments.add(PictureChildFragment.newInstance(mPictureBundle));
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mFragments.add(new PictureChildFragment());
        mVpContent.setPageTransformer(true, new ZoomOutPageTransformer());
        PictureOneAdapter mAdapter = new PictureOneAdapter(getChildFragmentManager(), mFragments);
        mVpContent.setAdapter(mAdapter);

        return view;
    }

    private void analyseArguments() {
        Bundle bundle = getArguments();
        mPictureBundle = bundle.getBundle(Constants.PICTURE_BUNDLE);
    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }

    @Override
    public void showError(String message) {
        SystemUtil.showToast(mActivity, message);
    }
}
