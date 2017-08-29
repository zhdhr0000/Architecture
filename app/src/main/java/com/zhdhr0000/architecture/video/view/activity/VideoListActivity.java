package com.zhdhr0000.architecture.video.view.activity;

import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.taobao.weex.ui.view.listview.ExtendedLinearLayoutManager;
import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseActivity;
import com.zhdhr0000.architecture.video.presenter.VideoPresenter;
import com.zhdhr0000.architecture.video.protocol.Video;
import com.zhdhr0000.architecture.video.view.MultiType;
import com.zhdhr0000.architecture.video.view.VideoListAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zhangyinghao on 2017/8/24.
 */

public class VideoListActivity extends BaseActivity<Video.Presenter> implements Video.View {

    public static String videoUrl = "https://img.wowdsgn.com/res/test/IMG_1730.MP4";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.video_fullscreen_container)
    FrameLayout videoFullscreenContainer;
    ListVideoUtil listVideoUtil;
    VideoListAdapter adapter;
    ExtendedLinearLayoutManager layoutManager;
    int firstItem;
    int lastItem;

    @Override
    public void showError(int type, String msg) {
        showToast(msg);
    }

    @Override
    protected Video.Presenter initPresenter() {
        return new VideoPresenter();
    }

    @Override
    protected void initDataAndEvent() {
        layoutManager = new ExtendedLinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listVideoUtil = new ListVideoUtil(this);
        listVideoUtil.setHideActionBar(true);
        listVideoUtil.setHideStatusBar(true);
        listVideoUtil.setAutoRotation(true);
        listVideoUtil.setNeedShowWifiTip(true);
        listVideoUtil.setFullLandFrist(false);
        listVideoUtil.setNeedLockFull(false);
        listVideoUtil.setFullViewContainer(videoFullscreenContainer);

        ArrayList<MultiType> data = new ArrayList<>();
        data.add(new MultiType(MultiType.VIDEO, videoUrl));
        data.add(new MultiType(MultiType.NOT_VIDEO, videoUrl));
        data.add(new MultiType(MultiType.VIDEO, videoUrl));
        data.add(new MultiType(MultiType.VIDEO, videoUrl));
        data.add(new MultiType(MultiType.NOT_VIDEO, videoUrl));
        data.add(new MultiType(MultiType.VIDEO, videoUrl));
        data.add(new MultiType(MultiType.VIDEO, videoUrl));
        data.add(new MultiType(MultiType.NOT_VIDEO, videoUrl));
        data.add(new MultiType(MultiType.VIDEO, videoUrl));
        data.add(new MultiType(MultiType.NOT_VIDEO, videoUrl));
        data.add(new MultiType(MultiType.VIDEO, videoUrl));
        data.add(new MultiType(MultiType.NOT_VIDEO, videoUrl));

        adapter = new VideoListAdapter(this, data);

        adapter.setListVideoUtil(listVideoUtil);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstItem = layoutManager.findFirstVisibleItemPosition();
                lastItem = layoutManager.findLastVisibleItemPosition();
                if (listVideoUtil.getPlayPosition() >= 0 && listVideoUtil.getPlayTAG().equals(VideoListAdapter.TAG)) {
                    int position = listVideoUtil.getPlayPosition();
                    if (position < firstItem || position > lastItem) {
//                        GSYVideoManager.onPause();
                        if (!listVideoUtil.isSmall() && !listVideoUtil.isFull()) {
                            int size = CommonUtil.dip2px(getApplicationContext(), 150);
                            listVideoUtil.showSmallVideo(new Point(size, (int) (size * 0.5625)), true, true);
                        }
                    } else {
//                        GSYVideoManager.onResume();
                        if (listVideoUtil.isSmall()) {
                            listVideoUtil.smallVideoToNormal();
                        }
                    }
                }
            }
        });
        listVideoUtil.setVideoAllCallBack(new VideoActivity.SimpleCallback() {
            @Override
            public void onQuitSmallWidget(String url, Object... objects) {
                super.onQuitSmallWidget(url, objects);
                if (listVideoUtil.getPlayPosition() >= 0 && listVideoUtil.getPlayTAG().equals(VideoListAdapter.TAG)) {
                    int position = listVideoUtil.getPlayPosition();
                    if (position < firstItem || position > lastItem) {
                        listVideoUtil.releaseVideoPlayer();
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (listVideoUtil.backFromFull()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listVideoUtil.releaseVideoPlayer();
        GSYVideoPlayer.releaseAllVideos();

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_video_list;
    }

}
