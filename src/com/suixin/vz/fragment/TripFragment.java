package com.suixin.vz.fragment;

import java.util.ArrayList;
import java.util.List;

import com.suixin.vy.ui.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class TripFragment extends Fragment implements OnClickListener {

    private List<View> views;

    private ViewPager pager;

    private int lineWidth;

    private View lineView;

    private TextView fragment_hot_listview_vz, fragment_listview_find_vz,
            fragment_attention_vz;

    private TextView[] tvs;

    private int currentPagerIndex = 0;

    private int screenWidth;

    private boolean isFrist = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_main, container,
                false);
        initView(view, inflater, container);
        initLine();
        // 实例化控件
        setTabTextViewColor(currentPagerIndex);
        addListener();
        setLine(lineWidth);
        pager.setCurrentItem(1);
        return view;

    }

    private void setTabTextViewColor(int pos) {
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setTextColor(Color.BLACK);
        }
        tvs[pos].setTextColor(
                getResources().getColor(android.R.color.holo_blue_light));
    }

    private void addListener() {
        fragment_hot_listview_vz.setOnClickListener(this);
        fragment_listview_find_vz.setOnClickListener(this);
        fragment_attention_vz.setOnClickListener(this);
        pager.setOnPageChangeListener(new OnPageChangeListener() {

            public void onPageSelected(int arg0) {
                currentPagerIndex = arg0;
                Toast.makeText(getActivity(), arg0 + "", 500).show();
                setTabTextViewColor(currentPagerIndex);
            }

            public void onPageScrolled(int position, float offset,
                    int positionOffsetPixels) {
                float flag = 0f;
                flag = position * lineWidth + (int) (offset * lineWidth);
                setLine(flag);
            }

            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    protected void setLine(float offset) {
        LinearLayout.LayoutParams lp = (LayoutParams) lineView
                .getLayoutParams();
        lp.width = lineWidth;
        lp.leftMargin = (int) offset;
        lineView.setLayoutParams(lp);
    }

    private void initLine() {
        screenWidth = getActivity().getWindow().getWindowManager()
                .getDefaultDisplay().getWidth();
        lineWidth = screenWidth / 3;
    }

    private void initView(View view, LayoutInflater inflater,
            ViewGroup container) {
        fragment_hot_listview_vz = (TextView) view.findViewById(R.id.tab1);
        fragment_listview_find_vz = (TextView) view.findViewById(R.id.tab2);
        fragment_attention_vz = (TextView) view.findViewById(R.id.tab3);
        tvs = new TextView[] { fragment_hot_listview_vz,
                fragment_listview_find_vz, fragment_attention_vz };

        pager = (ViewPager) view.findViewById(R.id.pager);

        lineView = view.findViewById(R.id.tab_line);
        views = new ArrayList<View>();
        View item1 = inflater.inflate(R.layout.fragment_hot_listview_vz,
                container, false);
        View item2 = inflater.inflate(R.layout.fragment_listview_find_vz,
                container, false);
        View item3 = inflater.inflate(R.layout.fragment_attention_vz, container,
                false);

        views.add(item1);
        views.add(item2);
        views.add(item3);

        pager.setAdapter(new MyPagerAdapter());
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position);
            container.removeView(view);
            container.addView(view);
            return view;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.tab1:
            pager.setCurrentItem(0);
            break;
        case R.id.tab2:
            pager.setCurrentItem(1);
            break;
        case R.id.tab3:
            pager.setCurrentItem(2);
            break;
        default:
            break;
        }
    }
}
