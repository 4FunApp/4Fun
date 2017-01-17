package com.joker.fourfun.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.joker.fourfun.Constants;
import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpActivity;
import com.joker.fourfun.model.Picture;
import com.joker.fourfun.presenter.PictureDetailPresenter;
import com.joker.fourfun.presenter.contract.PictureDetailContract;
import com.joker.fourfun.utils.GlideUtil;
import com.joker.fourfun.view.DragPhotoView;

import butterknife.BindView;

public class PictureDetailActivity extends BaseMvpActivity<PictureDetailContract.View,
        PictureDetailPresenter> implements
        PictureDetailContract.View {
    public int mPosition;
    public Picture mPicture;
    @BindView(R.id.dpv_content)
    DragPhotoView mDpvContent;

    public static Intent newInstance(AppCompatActivity activity, Bundle bundle) {
        Intent intent = new Intent(activity, PictureDetailActivity.class);
        intent.putExtra(Constants.PICTURE_DETAILS_BUNDLE, bundle);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        analyseIntent();
        mDpvContent.setOnExitListener(new DragPhotoView.OnExitListener() {
            @Override
            public void onExit(DragPhotoView view, float translateX, float translateY, float w, float h) {
            }
        });
        mDpvContent.setOnTapListener(new DragPhotoView.OnTapListener() {
            @Override
            public void onTap(DragPhotoView view) {
                finish();
            }
        });
        mPresenter.getPicture(mPicture, mPosition);
    }

    private void analyseIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.PICTURE_DETAILS_BUNDLE)) {
            Bundle bundle = intent.getBundleExtra(Constants.PICTURE_DETAILS_BUNDLE);
            mPicture = bundle.getParcelable(Constants.PICTURE_DETAILS_IMG);
            mPosition = bundle.getInt(Constants.PICTURE_DETAILS_ONE_POSITION, 0);
        }
    }


    @Override
    protected void setContentViewAndInject(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_picture_detail);
        getComponent().inject(this);
    }

    @Override
    public void showContent(Picture picture) {
        GlideUtil.setImage(this, picture.getPicUrl(), mDpvContent);
    }

    @Override
    public void showError(String message) {

    }
}
