package com.zhdhr0000.architecture.imagetransform;

import android.graphics.PorterDuff;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zhangyinghao on 2017/9/1.
 */

public class ImageTransformActivity extends BaseActivity<ImageTransform.Presenter> implements ImageTransform.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    ImageTransformAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void initDataAndEvent() {
        layoutManager = new LinearLayoutManager(this);
        adapter = new ImageTransformAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ArrayList<PorterDuff.Mode> list = new ArrayList<>();
        list.add(PorterDuff.Mode.ADD);
        list.add(PorterDuff.Mode.CLEAR);
        list.add(PorterDuff.Mode.DARKEN);
        list.add(PorterDuff.Mode.DST);
        list.add(PorterDuff.Mode.DST_ATOP);
        list.add(PorterDuff.Mode.DST_IN);
        list.add(PorterDuff.Mode.DST_OUT);
        list.add(PorterDuff.Mode.DST_OVER);
        list.add(PorterDuff.Mode.LIGHTEN);
        list.add(PorterDuff.Mode.MULTIPLY);
        list.add(PorterDuff.Mode.OVERLAY);
        list.add(PorterDuff.Mode.SCREEN);
        list.add(PorterDuff.Mode.XOR);
        list.add(PorterDuff.Mode.SRC);
        list.add(PorterDuff.Mode.SRC_ATOP);
        list.add(PorterDuff.Mode.SRC_IN);
        list.add(PorterDuff.Mode.SRC_OUT);
        list.add(PorterDuff.Mode.SRC_OVER);

        adapter.setDataset(list);
    }

    @Override
    public void showError(int type, String msg) {
        showToast(msg);
    }

    @Override
    protected ImageTransform.Presenter initPresenter() {
        return new ImageTransformPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_imagetransform;
    }
}
