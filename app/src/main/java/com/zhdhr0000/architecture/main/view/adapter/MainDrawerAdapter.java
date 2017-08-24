package com.zhdhr0000.architecture.main.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.app.Constants;

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
                tvPageName.setText("尝试RxJava");
                break;
            case Constants.TYPE_PROCCESSHEILD:
                tvPageName.setText("进程全局守护");
                break;
            case Constants.TYPE_JULIASET:
                tvPageName.setText("分形-朱利亚集");
                break;
            case Constants.TYPE_TINDER:
                tvPageName.setText("Tinder视图");
                break;
            case Constants.TYPE_WEEX:
                tvPageName.setText("尝试Weex");
                break;
            case Constants.TYPE_PARALLAX:
                tvPageName.setText("视差滚动");
                break;
            case Constants.TYPE_VIDEO:
                tvPageName.setText("视频播放demo");
                break;
            default:
                tvPageName.setText("[占位符]");
                break;
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

}
