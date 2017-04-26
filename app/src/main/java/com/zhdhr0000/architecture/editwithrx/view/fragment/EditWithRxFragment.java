package com.zhdhr0000.architecture.editwithrx.view.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.editwithrx.presenter.EditWithRxPresenter;
import com.zhdhr0000.architecture.editwithrx.protocol.EditWithRx;
import com.zhdhr0000.architecture.utils.RxUtil;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by zhangyh on 2017/3/1.
 */

public class EditWithRxFragment extends BaseFragment<EditWithRxPresenter> implements EditWithRx.View {

    @BindView(R.id.textview1)
    TextView textView1;
    @BindView(R.id.textview2)
    TextView textView2;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.edittext1)
    EditText editText1;
    @BindView(R.id.edittext2)
    EditText editText2;

    String str1 = "";
    String str2 = "";

    Subject<String> strsub1 = PublishSubject.create();
    Subject<String> strsub2 = PublishSubject.create();

    Observable<Subject<String>> obs1;
    Observable<Subject<String>> obs2;
    Observable<String> combined;

    Consumer<Subject> text1;
    Consumer<Subject> text2;
    Consumer<Subject> buttonConsumer;
    BiFunction<Subject, Subject, String> combinedFunction;

    @Override
    protected void initDataAndEvent() {
        obs1 = RxUtil.create(strsub1);
        obs2 = RxUtil.create(strsub2);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                str1 = s.toString();
            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                str2 = s.toString();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.commit(button.getText().toString());
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
    public void showContent1(String content) {
        textView1.setText(content + "");
    }

    @Override
    public void showContent2(String content) {
        textView2.setText(content + "");
    }

    @Override
    public void showToast(String content) {
        showToast(content);
    }

    @Override
    public void showError(int type, String msg) {
        textView1.setText(msg + "");
    }
}
