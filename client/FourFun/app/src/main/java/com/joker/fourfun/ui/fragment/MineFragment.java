package com.joker.fourfun.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.presenter.MinePresenter;
import com.joker.fourfun.presenter.contract.MineContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends BaseMvpFragment<MineContract.View, MinePresenter> implements
        MineContract.View {

    public boolean isNight = true;
    @BindView(R.id.tv_collect)
    TextView mTvCollect;
    @BindView(R.id.tv_browse)
    TextView mTvBrowse;
    @BindView(R.id.tv_setting)
    TextView mTvSetting;
    @BindView(R.id.tv_night)
    TextView mTvNight;

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

    @OnClick({R.id.tv_collect, R.id.tv_browse, R.id.tv_setting, R.id.tv_night})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_collect:
                break;
            case R.id.tv_browse:
                break;
            case R.id.tv_setting:
                break;
            case R.id.tv_night:
//                if (isNight) {
//                    ThemeModeContext.getInstance().setState(new DayState());
//                } else {
//                    ThemeModeContext.getInstance().setState(new NightState());
//                }
//                ThemeModeContext.getInstance().setAppTheme(mActivity);
                break;
        }
    }
}
