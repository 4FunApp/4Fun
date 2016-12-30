package com.joker.fourfun.ui.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.presenter.LoginPresenter;
import com.joker.fourfun.presenter.contract.LoginContract;
import com.joker.fourfun.utils.SystemUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends BaseMvpFragment<LoginContract.View, LoginPresenter> implements
        LoginContract.View {
    @BindView(R.id.fab_login)
    FloatingActionButton mLoginFab;
    RegisterFragment mFragment;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }

    @Override
    public void showMsg(String message) {
        SystemUtil.showToast(mActivity, message);
    }

    @Override
    public void showError(String message) {
        SystemUtil.showToast(mActivity, message);
    }

    @OnClick({R.id.bt_go, R.id.fab_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_login:
//                    mActivity.getWindow().setExitTransition(null);
//                    mActivity.getWindow().setEnterTransition(null);
//                    ActivityOptions options =
//                            ActivityOptions.makeSceneTransitionAnimation(mActivity, mLoginFab, mLoginFab
//                                    .getTransitionName
//                                            ());

//                    setExitTransition(new Fade());
//                    mFragment.setEnterTransition(new Fade());
//                    mFragment.setSharedElementReturnTransition(new DetailTransition());
//                    mFragment.setSharedElementEnterTransition(new DetailTransition());
                // 25.1.0以下的support包,Material过渡动画只有在进栈时有,返回时没有;
                // 25.1.0+的support包，SharedElement正常
//                    mFragment.transaction()
//                            .addSharedElement(((FirstHomeAdapter.VH) vh).img, getString(R.string
//                                    .image_transition))
//                            .addSharedElement(((FirstHomeAdapter.VH) vh).tvTitle, "tv")
//                            .commit();
                if (mFragment == null) {
                    mFragment = new RegisterFragment();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    mActivity.getWindow().setExitTransition(null);
                    mActivity.getWindow().setEnterTransition(null);
                    this.transaction()
                            .addSharedElement(mLoginFab, getString(R.string.translation_login_fab_tag))
                            .commit();
                }
                start(mFragment);
                break;
            default:
                break;
        }
    }
}
