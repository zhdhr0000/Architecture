package com.zhdhr0000.architecture.video.view.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.View;

import com.shuyu.gsyvideoplayer.listener.StandardVideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseActivity;
import com.zhdhr0000.architecture.video.presenter.VideoPresenter;
import com.zhdhr0000.architecture.video.protocol.Video;

import butterknife.BindView;

/**
 * Created by zhangyinghao on 2017/8/24.
 */

public class VideoActivity extends BaseActivity<Video.Presenter> implements Video.View {

    public static String videoUrl = "http://img.wowdsgn.com/res/test/IMG_1722.MP4";

    @BindView(R.id.gsy_video_player)
    StandardGSYVideoPlayer gsyVideoPlayer;

    private OrientationUtils orientationUtils;
    private boolean isPause;

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
        gsyVideoPlayer.setUp(videoUrl, true, "");
        gsyVideoPlayer.setDismissControlTime(5000);
        orientationUtils = new OrientationUtils(this, gsyVideoPlayer);
        orientationUtils.setRotateWithSystem(false);

        orientationUtils.releaseListener();
        gsyVideoPlayer.setIsTouchWiget(false);
        gsyVideoPlayer.setIsTouchWigetFull(true);
        gsyVideoPlayer.setLockLand(false);
        gsyVideoPlayer.setRotateViewAuto(false);
        gsyVideoPlayer.setSeekRatio(1);
        gsyVideoPlayer.startPlayLogic();
        if (gsyVideoPlayer.getFullscreenButton()!=null) {
            gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (orientationUtils != null) {
                        orientationUtils.resolveByClick();
                    }
                    gsyVideoPlayer.startWindowFullscreen(mActivity, true, true);
                }
            });
        }
        gsyVideoPlayer.setStandardVideoAllCallBack(new SimpleCallback() {
            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (!isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!gsyVideoPlayer.isIfCurrentIsFullscreen()) {
                    gsyVideoPlayer.startWindowFullscreen(this, true, true);
                }
            } else {
                if (gsyVideoPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (orientationUtils!=null){
            orientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)){
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        if (orientationUtils != null) orientationUtils.releaseListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_video;
    }

    public static class SimpleCallback implements StandardVideoAllCallBack {

        @Override
        public void onPrepared(String url, Object... objects) {

        }

        @Override
        public void onClickStartIcon(String url, Object... objects) {

        }

        @Override
        public void onClickStartError(String url, Object... objects) {

        }

        @Override
        public void onClickStop(String url, Object... objects) {

        }

        @Override
        public void onClickStopFullscreen(String url, Object... objects) {

        }

        @Override
        public void onClickResume(String url, Object... objects) {

        }

        @Override
        public void onClickResumeFullscreen(String url, Object... objects) {

        }

        @Override
        public void onClickSeekbar(String url, Object... objects) {

        }

        @Override
        public void onClickSeekbarFullscreen(String url, Object... objects) {

        }

        @Override
        public void onAutoComplete(String url, Object... objects) {

        }

        @Override
        public void onEnterFullscreen(String url, Object... objects) {

        }

        @Override
        public void onQuitFullscreen(String url, Object... objects) {

        }

        @Override
        public void onQuitSmallWidget(String url, Object... objects) {

        }

        @Override
        public void onEnterSmallWidget(String url, Object... objects) {

        }

        @Override
        public void onTouchScreenSeekVolume(String url, Object... objects) {

        }

        @Override
        public void onTouchScreenSeekPosition(String url, Object... objects) {

        }

        @Override
        public void onTouchScreenSeekLight(String url, Object... objects) {

        }

        @Override
        public void onPlayError(String url, Object... objects) {

        }

        @Override
        public void onClickStartThumb(String url, Object... objects) {

        }

        @Override
        public void onClickBlank(String url, Object... objects) {

        }

        @Override
        public void onClickBlankFullscreen(String url, Object... objects) {

        }
    }

}
