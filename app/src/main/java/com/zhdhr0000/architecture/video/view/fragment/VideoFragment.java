package com.zhdhr0000.architecture.video.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.video.presenter.VideoPresenter;
import com.zhdhr0000.architecture.video.protocol.Video;
import com.zhdhr0000.architecture.video.view.activity.VideoActivity;
import com.zhdhr0000.architecture.video.view.activity.VideoListActivity;

import butterknife.BindView;

/**
 * Created by zhangyinghao on 2017/8/21.
 */

public class VideoFragment extends BaseFragment<Video.Presenter> implements Video.View {

    @BindView(R.id.button_1)
    Button button1;
    @BindView(R.id.button_2)
    Button button2;
    @BindView(R.id.button_3)
    Button button3;
    @BindView(R.id.button_4)
    Button button4;

    @Override
    public void showError(int type, String msg) {
        showToast(msg);
    }

    @Override
    protected void initDataAndEvent() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, VideoActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, VideoActivity.class));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, VideoListActivity.class));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, VideoListActivity.class));
            }
        });
    }

    @Override
    protected Video.Presenter initPresenter() {
        return new VideoPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_video;
    }

}
