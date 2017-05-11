package com.zhdhr0000.architecture.tinder.view.fragment;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.tinder.presenter.TinderPresenter;
import com.zhdhr0000.architecture.tinder.protocol.Tinder;
import com.zhdhr0000.architecture.tinder.view.adapter.Tinder3Adapter;
import com.zhdhr0000.architecture.tinder.widget.SwipeCardViewKt;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by win7 on 2017/3/21.
 */

public class TinderFragment extends BaseFragment<TinderPresenter> implements Tinder.View {

    @BindView(R.id.swipecard)
    SwipeCardViewKt swipecard;

    @Override
    protected void initDataAndEvent() {
        Tinder3Adapter adapter = new Tinder3Adapter();
        ArrayList list = new ArrayList();
        for (int i = 0; i < 20; i++) list.add(i + "");
        adapter.setData(list);
        swipecard.setAdapter(adapter);
        swipecard.setMOnFlingListener(new SwipeCardViewKt.OnFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(@NotNull Object dataObject) {

            }

            @Override
            public void onRightCardExit(@NotNull Object dataObject) {

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });
        swipecard.invalidate();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new TinderPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tinder;
    }

    @Override
    public void showError(int type, String msg) {
    }

}
