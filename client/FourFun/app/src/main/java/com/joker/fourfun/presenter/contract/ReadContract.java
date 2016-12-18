package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;
import com.joker.fourfun.model.ArticleOne;

/**
 * Created by joker on 2016/12/8.
 */

public interface ReadContract {
    interface View extends BaseView {
        void showArticle(ArticleOne article);
    }

    interface Presenter extends BasePresenter<View> {
        void getArticle(int num);
    }
}
