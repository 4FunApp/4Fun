package com.joker.fourfun.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.model.PicOne;
import com.joker.fourfun.presenter.PictureChildPresenter;
import com.joker.fourfun.presenter.contract.PictureChildContract;
import com.joker.fourfun.utils.GlideUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureChildFragment extends BaseMvpFragment<PictureChildContract.View,
        PictureChildPresenter> implements PictureChildContract.View {
    @BindView(R.id.iv_content)
    ImageView mIvContent;
    private int mPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_child, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Logger.e("onLazyInitView");
        mPresenter.getContent(mPosition * -1);
    }

    public void setFragmentPosition(int position) {
        mPosition = position;
    }

    @Override
    public void showContent(PicOne picOne) {
        GlideUtil.setImage(mActivity, picOne.getPicUrl(), mIvContent);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }
}
