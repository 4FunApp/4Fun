package com.joker.fourfun.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joker.fourfun.Constants;
import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.model.Picture;
import com.joker.fourfun.presenter.PictureChildPresenter;
import com.joker.fourfun.presenter.contract.PictureChildContract;
import com.joker.fourfun.ui.PictureDetailActivity;
import com.joker.fourfun.utils.GlideUtil;
import com.joker.fourfun.utils.SystemUtil;
import com.joker.fourfun.view.DragPhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureChildFragment extends BaseMvpFragment<PictureChildContract.View,
        PictureChildPresenter> implements PictureChildContract.View {
    @BindView(R.id.iv_content)
    DragPhotoView mIvContent;
    @BindView(R.id.tv_VOL)
    TextView mTvVOL;
    @BindView(R.id.tv_des)
    TextView mTvDes;
    // 网页更新慢一天，所以默认值为 1
    private int mDay = 1;
    private Picture mPicture;

    public static PictureChildFragment newInstance(Bundle bundle) {
        PictureChildFragment fragment = new PictureChildFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_child, container, false);
        ButterKnife.bind(this, view);
        // 跳转
        mIvContent.setOnTapListener(new DragPhotoView.OnTapListener() {
            @Override
            public void onTap(DragPhotoView view) {
                try {
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(
                                    mActivity, view, Constants.TRANSIT_PIC);
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.PICTURE_DETAILS_ONE_POSITION, mDay);
                    bundle.putParcelable(Constants.PICTURE_DETAILS_IMG, mPicture);
                    ActivityCompat.startActivity(mActivity, PictureDetailActivity.newInstance(mActivity,
                            bundle),
                            optionsCompat.toBundle());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });
        mIvContent.setOnExitListener(new DragPhotoView.OnExitListener() {
            @Override
            public void onExit(DragPhotoView view, float translateX, float translateY, float w, float h) {
            }
        });

        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mPresenter.getContent(mDay * -1, bundle);
    }

    public void setFragmentPosition(int position) {
        // 网页更新慢一天 故 position 要+1
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
        this.mPicture = picture;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }

    @OnClick(R.id.iv_content)
    public void onClick() {
//        try {
//            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    mActivity, mIvContent, Constants.TRANSIT_PIC);
//            Bundle bundle = new Bundle();
//            bundle.putInt(Constants.PICTURE_DETAILS_ONE_POSITION, mDay);
//            bundle.putParcelable(Constants.PICTURE_DETAILS_IMG, mPicture);
//            ActivityCompat.startActivity(mActivity, PictureDetailActivity.newInstance(mActivity, bundle),
//                    optionsCompat.toBundle());
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
    }
}
