package com.zhdhr0000.architecture.imagetransform;

import android.graphics.PorterDuff;
import android.opengl.GLSurfaceView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhdhr0000.architecture.R;

import java.util.List;

import jp.co.cyberagent.android.gpuimage.GPUImage;

/**
 * Created by zhangyinghao on 2017/9/1.
 */

class ImageTransformAdapter extends RecyclerView.Adapter<ImageTransformAdapter.TestTransformVH> {

    List<PorterDuff.Mode> dataset;
//    GPUImage gpuImage;

    public ImageTransformAdapter() {
    }

    public ImageTransformAdapter(List<PorterDuff.Mode> dataset) {
        this.dataset = dataset;
    }

    @Override
    public TestTransformVH onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (gpuImage == null) gpuImage = new GPUImage(parent.getContext().getApplicationContext());
        return new TestTransformVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imagetransform, parent, false));
    }

    @Override
    public void onBindViewHolder(TestTransformVH holder, int position) {
        holder.image.setMode(dataset.get(position));
        holder.text.setText(dataset.get(position).name());
    }

    @Override
    public int getItemCount() {
        if (dataset != null) {
            return dataset.size();
        }
        return 0;
    }

    public List getDataset() {
        return dataset;
    }

    public void setDataset(List<PorterDuff.Mode> dataset) {
        this.dataset = dataset;
    }

    class TestTransformVH extends RecyclerView.ViewHolder {

        TestImageView image;
        TextView text;
//        GLSurfaceView gpuImageView;

        TestTransformVH(View itemView) {
            super(itemView);
//            gpuImageView = (GLSurfaceView) itemView.findViewById(R.id.gpu_image);
            text = (TextView) itemView.findViewById(R.id.text);
            image = (TestImageView) itemView.findViewById(R.id.image);
        }
    }

}
