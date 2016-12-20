package com.joker.fourfun.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.model.ArticleOne;
import com.joker.fourfun.presenter.ReadPresenter;
import com.joker.fourfun.presenter.contract.ReadContract;
import com.joker.fourfun.utils.SystemUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadFragment extends BaseMvpFragment<ReadContract.View, ReadPresenter> implements
        ReadContract.View {
    @BindView(R.id.tv_content)
    TextView mTvContent;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mPresenter.getArticle(1);
    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }

    @Override
    public void showError(String message) {
        SystemUtil.showToast(mActivity, message);
    }

    @Override
    public void showArticle(ArticleOne article) {
        String title = article.getArticleTitle();
        String author = article.getArticleAuthor();
        String content = article.getArticle();

        String text = title + "\n" + author + "\n\n" + content;
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new AbsoluteSizeSpan(SystemUtil.dip2px(mActivity, 30)), 0, title.length(), Spanned
                .SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new AbsoluteSizeSpan(SystemUtil.dip2px(mActivity, 20)), title.length() + 1, title
                .length() + 1 + author.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, title
                .length() + 1 + author.length(), Spanned
                .SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new AbsoluteSizeSpan(SystemUtil.dip2px(mActivity, 16)), title.length() + author
                .length() + 3, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        mTvContent.setText(style);
    }
}
