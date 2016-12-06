package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.presenter.contract.PictureContract;

import javax.inject.Inject;

/**
 * Created by joker on 2016/12/5.
 */

public class PicturePresenter extends BaseMvpPresenter<PictureContract.View> implements PictureContract.Presenter  {
    @Inject
    public PicturePresenter() {
    }
}
