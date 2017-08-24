package com.zhdhr0000.architecture.video.view.activity;

import android.app.Activity;
import android.databinding.adapters.ViewBindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.taobao.weex.ui.view.listview.ExtendedLinearLayoutManager;
import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseActivity;
import com.zhdhr0000.architecture.video.presenter.VideoPresenter;
import com.zhdhr0000.architecture.video.protocol.Video;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangyinghao on 2017/8/24.
 */

public class VideoListActivity extends BaseActivity<Video.Presenter> implements Video.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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
        recyclerView.setLayoutManager(new ExtendedLinearLayoutManager(this));
//        recyclerView.setAdapter();
        ListVideoUtil listVideoUtil;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_video_list;
    }

    private class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder> {

        private List dataset;

        public VideoListAdapter(Activity activity, List dataset) {
            this.dataset = dataset;
        }

        @Override
        public VideoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VideoListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parallax, parent, false));
        }

        @Override
        public void onBindViewHolder(VideoListViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            if (dataset != null)
                return dataset.size();
            return 0;
        }

        @Override
        public void onViewAttachedToWindow(VideoListViewHolder holder) {
            super.onViewAttachedToWindow(holder);
        }

        @Override
        public void onViewDetachedFromWindow(VideoListViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
        }

        class VideoListViewHolder extends RecyclerView.ViewHolder {
            StandardGSYVideoPlayer player;
            OrientationUtils utils;

            VideoListViewHolder(View itemView) {
                super(itemView);
                player = (StandardGSYVideoPlayer) itemView.findViewById(R.id.gsy_video_player);
            }

        }


    }
}
