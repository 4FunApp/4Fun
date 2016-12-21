package com.joker.fourfun.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.joker.fourfun.R;
import com.joker.fourfun.utils.SystemUtil;

/**
 * Created by joker on 2016/12/9.
 */

public class VerticalTextView extends View {
    private Paint paint;
    private int mWidth = 0;
    private int mHeight = 0;
    private int mFontHeight = 0;
    private float mFontSize = 24;
    private int mLineWidth = 0;
    private int textLength = 0;
    private String text = "";

    public VerticalTextView(Context context) {
        this(context, null);
    }

    public VerticalTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWith(context, attrs);
    }

    private void initWith(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.VerticalTextView, 0, 0);
        try {
            float textSizePixel = typedArray.getDimension(
                    R.styleable.VerticalTextView_rowTextSize,
                    getResources().getDimension(R.dimen.vertical_text_view_normal_text_size));
            int textSizeSp = SystemUtil.px2sp(context, textSizePixel);
            mFontSize = SystemUtil.sp2px(context, textSizeSp);
        } finally {
            typedArray.recycle();
        }
        Typeface typeface = SystemUtil.getTypeface(context);
        setTypeface(typeface);
    }

    //设置文字
    public final void setText(String text) {
        this.text = text;
        this.textLength = text.length();
        requestLayout();
        invalidate();
    }

    //设置字体大小
    public final void setTextSize(float size) {
        if (size != paint.getTextSize()) {
            mFontSize = size;
            requestLayout();
            invalidate();
        }
    }

    //设置字体颜色
    public final void setTextColor(int color) {
        paint.setColor(color);
        invalidate();
    }

    //设置字体颜色
    public final void setTextARGB(int a, int r, int g, int b) {
        paint.setARGB(a, r, g, b);
        invalidate();
    }

    //设置字体
    public void setTypeface(Typeface tf) {
        if (this.paint.getTypeface() != tf) {
            this.paint.setTypeface(tf);
            invalidate();
        }
    }

    //设置行宽
    public void setLineWidth(int LineWidth) {
        mLineWidth = LineWidth;
        requestLayout();
        invalidate();
    }

    //获取实际宽度
    public int getTextWidth() {
        return mWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画字
        drawMultipleVerticalText(canvas);
    }

    private void drawMultipleVerticalText(Canvas canvas) {
        char ch;
        int mTextPosY = 0;
        int mTextPosX = mWidth - mLineWidth;
        for (int i = 0; i < this.textLength; i++) {
            ch = text.charAt(i);
            if (ch == '\n') {
                mTextPosX -= mLineWidth;// 换列
                mTextPosY = 0;
            } else {
                mTextPosY += mFontHeight;
                if (mTextPosY > this.mHeight) {
                    mTextPosX -= mLineWidth;// 换列
                    i--;
                    mTextPosY = 0;
                } else {
                    canvas.drawText(String.valueOf(ch), mTextPosX, mTextPosY, paint);
                }
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHeight = measureHeight(heightMeasureSpec);
        mWidth = measureWidth();
        setMeasuredDimension(mWidth, mHeight);
    }

    private int measureHeight(int measureSpec) {
        measureFontHeight();
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {
            result = mFontHeight * text.length();
            if (result > specSize) {
                result = specSize;
            }
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }

    private void measureLineWidth() {
        //获得字宽
        if (mLineWidth == 0) {
            float[] widths = new float[1];
            paint.getTextWidths("正", widths);//获取单个汉字的宽度
            float[] space = new float[1];
            paint.getTextWidths(" ", space);
            mLineWidth = (int) Math.ceil((widths[0] + space[0]) * 1.1 + 2);
        }
    }

    private void measureFontHeight() {
        Paint.FontMetrics fm = paint.getFontMetrics();
        mFontHeight = (int) (Math.ceil(fm.descent - fm.top) * 0.9);// 获得字体高度
    }

    private int measureWidth() {
        char ch;
        int h = 0;
        paint.setTextSize(mFontSize);
        measureLineWidth();
        measureFontHeight();
        //计算文字行数
        int mRealLine = 0;
        for (int i = 0; i < this.textLength; i++) {
            ch = this.text.charAt(i);
            if (ch == '\n') {
                mRealLine++;// 真实的行数加一
                h = 0;
            } else {
                h += mFontHeight;
                if (h > this.mHeight) {
                    mRealLine++;// 真实的行数加一
                    i--;
                    h = 0;
                } else {
                    if (i == this.textLength - 1) {
                        mRealLine++;// 真实的行数加一
                    }
                }
            }
        }
        mRealLine++;//额外增加一行
        return mLineWidth * mRealLine;//计算文字总宽度
    }
}