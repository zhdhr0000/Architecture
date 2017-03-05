package com.zhdhr0000.architecture.view.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.presenter.EditWithRxPresenter;
import com.zhdhr0000.architecture.protocol.EditWithRx;

import butterknife.BindView;

/**
 * Created by zhangyh on 2017/3/1.
 *
 */

public class EditWithRxFragment extends BaseFragment<EditWithRxPresenter> implements EditWithRx.View {

    @BindView(R.id.textview)
    TextView textView;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.edittext)
    EditText editText;

    @Override
    protected void init() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                mPresenter.getContent(s.toString());
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.commit(editText.getText().toString().trim());
            }
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter = new EditWithRxPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_edit_with_rx;
    }

    @Override
    public void showContent(String content) {
        textView.setText(content + "");
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(mActivity, content + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(int type, String msg) {
        textView.setText(msg + "");
    }
}
