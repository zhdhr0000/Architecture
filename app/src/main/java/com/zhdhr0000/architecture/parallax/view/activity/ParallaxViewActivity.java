package com.zhdhr0000.architecture.parallax.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseActivity;
import com.zhdhr0000.architecture.parallax.presenter.ParallaxPresenter;
import com.zhdhr0000.architecture.parallax.protocol.Parallax;
import com.zhdhr0000.architecture.parallax.view.adapter.ParallaxAdapter;
import com.zhdhr0000.architecture.parallax.view.adapter.ParallaxLandAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangyinghao on 2017/8/29.
 */

public class ParallaxViewActivity extends BaseActivity<Parallax.Presenter> implements Parallax.View {

    static String[] data = {
            "https://img.wowdsgn.com/instagram/category/images/1ee6b8e8-8226-4779-90dd-eb5957489006_2dimension_392x238.png",
            "https://img.wowdsgn.com/instagram/category/images/8377a668-c8b7-40eb-bbfe-0e71f88aa046_2dimension_395x240.png",
            "https://img.wowdsgn.com/instagram/category/images/e7240f7f-9bf6-4930-ba4d-24f2478d60b0_2dimension_382x256.png",
            "https://img.wowdsgn.com/instagram/category/images/aa4770d3-2dab-4e01-9289-c9f57ee326d8_2dimension_471x288.png",
            "https://img.wowdsgn.com/instagram/category/images/43d1799f-0fe4-4ac5-be79-0cc4b43b608d_2dimension_402x286.png",
            "https://img.wowdsgn.com/instagram/category/images/fb1292be-5265-4802-ab50-5c1db462c872_2dimension_391x255.png",
            "https://img.wowdsgn.com/social/insta/026de96b72f140649549320135297b911491813654803026de96b72f140649549320135297b911491813654804_2dimension_750x750",
            "https://img.wowdsgn.com/social/insta/0WSeHw_1494721860376_2dimension_1224x1224",
            "https://img.wowdsgn.com/social/insta/17HBUEf1d4840b615d4de397af20afa40f7dc51491567630348_2dimension_828x828",
            "https://img.wowdsgn.com/social/insta/1WT8H7_1493640270164_2dimension_1080x1080",
            "https://img.wowdsgn.com/social/insta/1Yc8IP6f17fab5c0dc4d92ab7b633a86490fca1493169903783_2dimension_539x404",
            "https://img.wowdsgn.com/social/insta/1wu4He_1494570158007_2dimension_864x864"
    };

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    List<String> dataset = new ArrayList<>();
    RecyclerView.Adapter adapter;

    public static void startVertical(Context context) {
        Intent intent = new Intent(context, ParallaxViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initDataAndEvent() {
        Collections.addAll(dataset, data);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            adapter = new ParallaxLandAdapter(dataset);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ParallaxAdapter(dataset);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Parallax.Presenter initPresenter() {
        return new ParallaxPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_parallax_view;
    }

    @Override
    public void showError(int type, String msg) {
        showToast(msg);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            adapter = new ParallaxLandAdapter(dataset);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ParallaxAdapter(dataset);
        }
        recyclerView.setAdapter(adapter);
    }

}
