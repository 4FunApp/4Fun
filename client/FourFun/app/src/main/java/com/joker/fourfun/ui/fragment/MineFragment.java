package com.joker.fourfun.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.presenter.MinePresenter;
import com.joker.fourfun.presenter.contract.MineContract;

import butterknife.ButterKnife;

public class MineFragment extends BaseMvpFragment<MineContract.View, MinePresenter> implements
        MineContract.View {

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }

    @Override
    public void showError(String message) {

    }
}
