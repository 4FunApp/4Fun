package com.joker.fourfun.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

import com.joker.fourfun.utils.SystemUtil;

/**
 * Created by joker on 2016/12/20.
 */

public class DividerItemDecoration extends ItemDecoration {
    int mSpace;

    /**
     * @param space 传入的值，其单位视为dp
     */
    public DividerItemDecoration(int space) {
        this.mSpace = SystemUtil.dp2px(space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        int pos = parent.getChildAdapterPosition(view);

        outRect.left = 0;
        outRect.bottom = 0;
        outRect.right = 0;
        // 每个 item 都设置间隔
        outRect.top = mSpace;
    }
}
