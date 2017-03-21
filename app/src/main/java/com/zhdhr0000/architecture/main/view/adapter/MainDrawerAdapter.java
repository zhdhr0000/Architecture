package com.zhdhr0000.architecture.main.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.app.Constants;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.editwithrx.view.fragment.EditWithRxFragment;
import com.zhdhr0000.architecture.juliaset.view.fragment.JuliaSetFragment;
import com.zhdhr0000.architecture.processsheild.view.fragment.ProcessSheildFragment;
import com.zhdhr0000.architecture.tinder.view.fragment.TinderFragment;

import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by win7 on 2017/3/6.
 */

public class MainDrawerAdapter extends BaseAdapter {

    List<Integer> data;
    SupportActivity mContext;

    public MainDrawerAdapter(SupportActivity context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public Integer getItem(int position) {
        if (data != null) {
            return data.get(position);
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvPageName;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_main_drawer, parent, false);
            tvPageName = (TextView) convertView.findViewById(R.id.tv_page_name);
            convertView.setTag(tvPageName);
        } else {
            tvPageName = (TextView) convertView.getTag();
        }
        switch (data.get(position)) {
            case Constants.TYPE_EDITWITHRX:
                tvPageName.setText(EditWithRxFragment.class.getSimpleName());
                break;
            case Constants.TYPE_PROCCESSHEILD:
                tvPageName.setText(ProcessSheildFragment.class.getSimpleName());
                break;
            case Constants.TYPE_JULIASET:
                tvPageName.setText(JuliaSetFragment.class.getSimpleName());
                break;
            case Constants.TYPE_TINDER:
                tvPageName.setText(TinderFragment.class.getSimpleName());
                break;
            default:
                tvPageName.setText(BaseFragment.class.getSimpleName());
                break;
        }
        return convertView;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

}
