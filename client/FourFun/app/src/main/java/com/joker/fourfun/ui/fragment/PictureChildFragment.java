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
import android.widget.ImageView;
import android.widget.TextView;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.model.Picture;
import com.joker.fourfun.presenter.PictureChildPresenter;
import com.joker.fourfun.presenter.contract.PictureChildContract;
import com.joker.fourfun.utils.GlideUtil;
import com.joker.fourfun.utils.SystemUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureChildFragment extends BaseMvpFragment<PictureChildContract.View,
        PictureChildPresenter> implements PictureChildContract.View {
    @BindView(R.id.iv_content)
    ImageView mIvContent;
    @BindView(R.id.tv_VOL)
    TextView mTvVOL;
    @BindView(R.id.tv_des)
    TextView mTvDes;
    // 网页更新慢一天，所以默认值为 1
    private int mDay = 1;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_child, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mPresenter.getContent(mDay * -1);
    }

    public void setFragmentPosition(int position) {
        // 网页更新慢一天 故要 +1
        mDay = position + 1;
    }

    @Override
    public void showContent(Picture picture) {
        String vol = picture.getVOL();
        String authorWork = picture.getAuthorWork();
        String description = picture.getPicDescription();
        String date = picture.getPicDate().replace("-", " ");

        String content = authorWork + "\n\n" + description + "\n\n" + date;
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        // 描述 居中
        style.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL), authorWork.length() + 1,
                authorWork.length() + description.length() + 1
                , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        // 描述 字体大小
        style.setSpan(new AbsoluteSizeSpan(SystemUtil.dp2px(14)), authorWork.length() + 1,
                authorWork.length() + description.length() + 1
                , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        // 作者 偏右
//        style.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), 0, authorWork.length()
//                        - 1,
//                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        // 日期 偏右
//        style.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), authorWork.length() +
//                description.length() + 2,
//                content.length()
//                , Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        mTvDes.setText(style);
        mTvVOL.setText(vol);
        GlideUtil.setImage(mActivity, picture.getPicUrl(), mIvContent);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }
}
