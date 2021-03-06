package com.zhiyun88.www.module_main.main.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.wb.baselib.adapter.ViewPageTabAdapter;
import com.wb.baselib.base.fragment.LazyFragment;
import com.wb.baselib.view.TopBarView;
import com.zhiyun88.www.module_main.R;

import java.util.ArrayList;

public class TaskFragment extends LazyFragment {

    private TopBarView topBarView;
    private ScrollIndicatorView scrollIndicatorView;
    private ViewPager mViewPager;

    @Override
    public boolean isLazyFragment() {
        return true;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.main_fragment_task);
        topBarView = getViewById(R.id.topbarview);
        scrollIndicatorView = getViewById(R.id.spring_indicator);
        mViewPager = getViewById(R.id.viewpager);
        initView();
    }

    @Override
    public void initView() {
        topBarView.getCenterTextView().setText(R.string.main_my_task);
        topBarView.getLeftImageButton().setVisibility(View.GONE);

        ArrayList<String> str = new ArrayList<>();
        ArrayList<Fragment> mFragments = new ArrayList<>();
        str.add(getString(R.string.main_to_be_completed));
        str.add(getString(R.string.main_off_the_stocks));
        str.add(getString(R.string.main_task_overdue));
        mFragments.add(MyTaskFragment.newInstance(0));//待完成
        mFragments.add(MyTaskFragment.newInstance(1));//已完成
        mFragments.add(MyTaskFragment.newInstance(2));//任务逾期
        scrollIndicatorView.setSplitAuto(true);
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener() {
            @Override
            public TextView getTextView(View tabItemView, int position) {
                return (TextView) tabItemView.findViewById(R.id.test_tv);
            }
        }.setColor(getResources().getColor(R.color.main_text_blue_458), Color.BLACK));
        ColorBar colorBar = new ColorBar(getActivity(), getResources().getColor(R.color.main_text_blue_458), 4);
        scrollIndicatorView.setScrollBar(colorBar);
        IndicatorViewPager indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, mViewPager);
        ViewPageTabAdapter viewPageTabAdapter= new ViewPageTabAdapter(getChildFragmentManager(), getActivity(), mFragments, str);
        indicatorViewPager.setAdapter(viewPageTabAdapter);
        mViewPager.setOffscreenPageLimit(mFragments.size());
    }
}
