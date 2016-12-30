package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.presenter.contract.MineContract;

import javax.inject.Inject;

/**
 * Created by joker on 2016/12/27.
 */

public class MinePresenter extends BaseMvpPresenter<MineContract.View> implements MineContract
        .Presenter {
    @Inject
    MinePresenter() {
    }
}
