package com.zhdhr0000.architecture.video.view;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;
import com.zhdhr0000.architecture.R;

import java.util.List;

/**
 * Created by zhangyinghao on 2017/8/25.
 */

public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MultiType> dataset;
    public static final String TAG = "this is a tag";
    private ListVideoUtil listVideoUtil;

    public VideoListAdapter(Activity activity, List<MultiType> dataset) {
        this.dataset = dataset;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MultiType.VIDEO) {
            return new VideoListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
        } else {
            return new VideoNotVideoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_video, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof VideoListViewHolder) {

            listVideoUtil.addVideoPlayer(position, ((VideoListViewHolder) holder).cover, TAG, ((VideoListViewHolder) holder).videoContainer, ((VideoListViewHolder) holder).videoButton);
            ((VideoListViewHolder) holder).videoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyDataSetChanged();
                     listVideoUtil.setPlayPositionAndTag(position, TAG);
                    listVideoUtil.setTitle(dataset.get(position).getContent());
                    listVideoUtil.startPlay(dataset.get(position).getContent());
                }
            });
        }

        if (holder instanceof VideoNotVideoHolder) {
            ((VideoNotVideoHolder) holder).textView.setText(dataset.get(position).getContent() + "");
        }
    }

    @Override
    public int getItemCount() {
        if (dataset != null)
            return dataset.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataset != null) {
            return dataset.get(position).getType();
        }
        return super.getItemViewType(position);
    }

    public void setListVideoUtil(ListVideoUtil listVideoUtil) {
        this.listVideoUtil = listVideoUtil;
    }

    class VideoListViewHolder extends RecyclerView.ViewHolder {

        FrameLayout videoContainer;
        ImageView videoButton;

        ImageView cover;

        VideoListViewHolder(View itemView) {
            super(itemView);
            videoContainer = (FrameLayout) itemView.findViewById(R.id.video_container);
            videoButton = (ImageView) itemView.findViewById(R.id.video_button);
            cover = new ImageView(itemView.getContext());
            cover.setScaleType(ImageView.ScaleType.CENTER_CROP);
            cover.setImageResource(R.mipmap.dog);
        }
    }

    class VideoNotVideoHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public VideoNotVideoHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}

