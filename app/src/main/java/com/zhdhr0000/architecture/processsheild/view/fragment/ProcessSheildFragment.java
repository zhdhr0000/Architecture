package com.zhdhr0000.architecture.processsheild.view.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.processsheild.widget.GuardianAngel;
import com.zhdhr0000.architecture.processsheild.presenter.ProcessSheildPresenter;
import com.zhdhr0000.architecture.processsheild.protocol.ProcessSheild;
import com.zhdhr0000.architecture.processsheild.protocol.ProcessSheild.Presenter;

import butterknife.BindView;

/**
 * Created by win7 on 2017/3/6.
 */

public class ProcessSheildFragment extends BaseFragment<Presenter> implements ProcessSheild.View {

    @BindView(R.id.btn_make_java_crash)
    Button mBtnMakeJavaCrash;
    @BindView(R.id.btn_make_native_crash)
    Button mBtnMakeNativeCrash;
    @BindView(R.id.btn_start_guardian)
    Button mBtnStartGuardian;
    @BindView(R.id.tv_content)
    TextView mContent;
    GuardianAngel.ExceptionHandler sExceptionHandler = new GuardianAngel.ExceptionHandler() {
        @Override
        public void onException(Throwable throwable) {
            if (mContent != null) {
                mContent.setText(throwable.getStackTrace().toString() + "");
            }
        }
    };

    @Override
    public void showError(int type, String msg) {
        mContent.setText(msg + "");
    }

    @Override
    protected void initDataAndEvent() {
        mBtnMakeJavaCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.makeJavaCrash();
            }
        });
        mBtnMakeNativeCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.makeNativeCrash();
            }
        });
        mBtnStartGuardian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardianAngel.protect(sExceptionHandler);
            }
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ProcessSheildPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_process_sheild;
    }
}
