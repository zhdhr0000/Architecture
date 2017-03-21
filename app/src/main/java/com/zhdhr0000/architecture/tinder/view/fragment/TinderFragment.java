package com.zhdhr0000.architecture.tinder.view.fragment;

import android.support.v7.widget.RecyclerView;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.tinder.presenter.TinderPresenter;
import com.zhdhr0000.architecture.tinder.protocol.Tinder;
import com.zhdhr0000.architecture.tinder.view.adapter.Tinder2Adapter;
import com.zhdhr0000.architecture.tinder.view.adapter.Tinder2LayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by win7 on 2017/3/21.
 */

public class TinderFragment extends BaseFragment<TinderPresenter> implements Tinder.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    public void showError(int type, String msg) {

    }

    @Override
    protected void initDataAndEvent() {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            data.add("position" + i);
        }
        recyclerView.setAdapter(new Tinder2Adapter(data));
        recyclerView.setLayoutManager(new Tinder2LayoutManager(mActivity, mView.getMeasuredWidth()));
    }

    @Override
    protected void initPresenter() {
        mPresenter = new TinderPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tinder;
    }

}
