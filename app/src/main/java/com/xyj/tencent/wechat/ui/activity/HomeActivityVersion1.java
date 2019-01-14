package com.xyj.tencent.wechat.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.xyj.tencent.R;
import com.xyj.tencent.common.base.BaseActivity;
import com.xyj.tencent.common.base.Global;
import com.xyj.tencent.common.ui.GradientTab;
import com.xyj.tencent.wechat.ui.adapter.MyFragmentAdapter;
import com.xyj.tencent.wechat.ui.fragment.MainFragment1;
import com.xyj.tencent.wechat.ui.fragment.MainFragment2;
import com.xyj.tencent.wechat.ui.fragment.MainFragment3;

import java.util.ArrayList;
import java.util.List;

public class HomeActivityVersion1 extends BaseActivity {
    private String[] titles = new String[] {
            "会话", "联系人", "设置"
    };

    private int[] icons = new int[] {
            R.drawable.new_message,
            R.drawable.new_address_book,
            R.drawable.new_mine,
    };


    private int[] iconsSelected = new int[] {
            R.drawable.new_message_foc,
            R.drawable.new_address_book_foc,
            R.drawable.new_mine_foc,
    };

    private LinearLayout ll_tab_layout;
    private ViewPager view_pager;
    /** 选项卡控件 */
    private GradientTab[] mTabs = new GradientTab[3];
    /** 当前选中的选项卡 */
    private GradientTab mCurrentTab;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        Global.setNoStatusBarFullMode(this);
        ll_tab_layout = findView(R.id.ll_tab_layout);
        view_pager = findView(R.id.view_pager);

        initTab();
        initViewPager();
    }





    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        MainFragment1  mainFragment1 = new MainFragment1();

        fragments.add(mainFragment1);
        fragments.add(new MainFragment2());
        fragments.add(new MainFragment3());

        view_pager.setAdapter(new MyFragmentAdapter(
                getSupportFragmentManager(), fragments));
    }

    private void initTab() {
        int padding = Global.dp2px(5); // 5dp
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT);
        param.weight = 1;   // 宽度平分
        for (int i = 0; i < titles.length; i++) {
            GradientTab tab = new GradientTab(this);
            mTabs[i] = tab;
            tab.setTag(i);  // 设置标识
            // 设置选项卡点击事件
            tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    // 选项卡切换了
                    // onTabSelected(position);
                    // false： 禁用切换的动画
                    view_pager.setCurrentItem(position, false);
                }
            });

            // 设置标题和图标
            tab.setTextAndIcon(titles[i], icons[i], iconsSelected[i]);
            // 指定高亮显示的颜色
            tab.setHighlightColor(getResources().getColor(R.color.btn_green_bg));
            tab.setPadding(0, padding, 0, padding);

            // 设置未读条数
            // tab.setUnreadCount(3);
            // 有新消息: 显示红点
            // tab.setRedDotVisible(true);
            ll_tab_layout.addView(tab, param);
        }

        mCurrentTab = mTabs[0];  // 默认选中第一个
        mCurrentTab.setTabSelected(true);   // 选中，会高亮
    }


    /** 选项卡切换了*/
    private void onTabSelected(int position) {
        mCurrentTab.setTabSelected(false);  // 取消高亮

        mCurrentTab = mTabs[position];

        mCurrentTab.setTabSelected(true);   // 设置为高亮
    }


    @Override
    public void initListener() {
        view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                onTabSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v, int id) {

    }
}
