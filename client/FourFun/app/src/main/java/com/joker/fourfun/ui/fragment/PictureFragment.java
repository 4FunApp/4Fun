package com.joker.fourfun.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.model.PicOne;
import com.joker.fourfun.presenter.PicturePresenter;
import com.joker.fourfun.presenter.contract.PictureContract;
import com.joker.fourfun.utils.SystemUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends BaseMvpFragment<PicturePresenter> implements PictureContract.View {
    @Override
    public void showError(String message) {
        SystemUtil.showToast(mActivity, message);
    }

    @Override
    public void showContent(PicOne picOne) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, container, false);

        return view;
    }
}
