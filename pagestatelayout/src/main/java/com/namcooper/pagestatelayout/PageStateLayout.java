package com.namcooper.pagestatelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Project: demos
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2018/10/29
 * Copyright notice:
 */
public class PageStateLayout extends RelativeLayout {

    private FrameLayout stateView;
    private View contentView;
    private FrameLayout.LayoutParams stateChildParams = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private OnPageStateClickListener mListener;
    private static final String stateViewTag = "state";

    public PageStateLayout(Context context) {
        this(context, null);
    }

    public PageStateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PageStateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取stateView
        stateView = new FrameLayout(getContext());
        stateView.setTag(stateViewTag);
        RelativeLayout.LayoutParams stateParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        stateParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        stateView.setLayoutParams(stateParams);
        stateView.setVisibility(GONE);
        //将stateView加入容器中
        this.addView(stateView);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取contentView
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (!stateViewTag.equals(child.getTag())) {
                contentView = child;
                break;
            }
        }
        if (contentView == null) {
            //说明没有设置子view，那可不行
            throw new RuntimeException("PageStateLayout should have at least one child!");
        }
    }

    /**
     * 展示内容
     */
    public void showContent() {
        stateView.setVisibility(GONE);
        contentView.setVisibility(VISIBLE);
    }

    /**
     * 展示等待状态，使用默认样式
     */
    public void showLoading() {
        View view = View.inflate(getContext(), R.layout.default_loading_view, null);
        showLoading(view);
    }

    /**
     * 展示等待状态，使用自定义样式
     *
     * @param view 自定义的等待状态样式
     */
    public void showLoading(View view) {
        stateView.removeAllViews();
        stateView.addView(view);
        contentView.setVisibility(GONE);
        stateView.setVisibility(VISIBLE);
    }

    /**
     * 展示空状态，使用默认样式
     */
    public void showEmpty() {
        View view = View.inflate(getContext(), R.layout.default_empty_view, null);
        showEmpty(view);
    }

    /**
     * 展示空状态，使用自定义样式
     *
     * @param view 自定义的空状态样式
     */
    public void showEmpty(View view) {
        stateView.removeAllViews();
        stateView.addView(view);
        contentView.setVisibility(GONE);
        stateView.setVisibility(VISIBLE);
        if (mListener != null) {
            view.setOnClickListener(v -> {
                mListener.onEmpty();
            });
        }
    }

    /**
     * 展示网络错误状态，使用默认样式
     */
    public void showNetError() {
        View view = View.inflate(getContext(), R.layout.default_net_error_view, null);
        showNetError(view);
    }

    /**
     * 展示网络错误状态，使用自定义样式
     *
     * @param view 自定义的网络错误状态样式
     */
    public void showNetError(View view) {
        stateView.removeAllViews();
        stateView.addView(view);
        contentView.setVisibility(GONE);
        stateView.setVisibility(VISIBLE);
        if (mListener != null) {
            view.setOnClickListener(v -> {
                mListener.onNetError();
            });
        }
    }

    /**
     * 展示数据错误状态，使用默认样式
     */
    public void showDataError() {
        View view = View.inflate(getContext(), R.layout.default_data_error_view, null);
        showNetError(view);
    }

    /**
     * 展示数据错误状态，使用自定义样式
     *
     * @param view 自定义的数据错误状态样式
     */
    public void showDataError(View view) {
        stateView.removeAllViews();
        stateView.addView(view);
        contentView.setVisibility(GONE);
        stateView.setVisibility(VISIBLE);
        if (mListener != null) {
            view.setOnClickListener(v -> {
                mListener.onDataError();
            });
        }
    }

    /**
     * 展示自定义的状态
     *
     * @param view 自定义状态下展示的view
     */
    public void showDiyState(View view) {
        stateView.removeAllViews();
        stateView.addView(view);
        contentView.setVisibility(GONE);
        stateView.setVisibility(VISIBLE);
        if (mListener != null) {
            view.setOnClickListener(v -> {
                mListener.onDiyState();
            });
        }
    }

    public void setOnPageStateClickListener(OnPageStateClickListener listener) {
        this.mListener = listener;
    }
}
