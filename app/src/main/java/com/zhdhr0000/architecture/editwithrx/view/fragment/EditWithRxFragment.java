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
import com.zhdhr0000.architecture.tinder.widget.TestBean;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
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

    Subject<String> sub1;
    Subject<String> sub2;

    Observable<String> combined;

    Consumer<String> text1;
    Consumer<String> text2;
    Consumer<String> buttonConsumer;
    BiFunction<String, String, String> combinedFunction;

    @Override
    protected void initDataAndEvent() {
        text1 = new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                textView1.setText(s);
            }
        };
        text2 = new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                textView2.setText(s);
            }
        };
        combinedFunction = new BiFunction<String, String, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull String s2) throws Exception {
                return s + s2;
            }
        };
        buttonConsumer = new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                button.setText(s);
            }
        };

        sub1 = PublishSubject.create();
        sub2 = PublishSubject.create();

        sub1.subscribe(text1);
        sub2.subscribe(text2);

        combined = PublishSubject.combineLatest(sub1, sub2, combinedFunction);
        combined.subscribe(buttonConsumer);

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
                sub1.onNext(str1);
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
                sub2.onNext(str2);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.commit(button.getText().toString());
            }
        });

        TestBean bean = new TestBean(10, 20,"strC","strD");
        editText1.setText(String.valueOf(bean.getA()));
        editText2.setText(String.valueOf(bean.getC()));
        textView1.setText(bean.getB());
        textView2.setText(bean.getD());
    }

    @Override
    protected EditWithRxPresenter initPresenter() {
        return new EditWithRxPresenter();
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
        super.showToast(content);
    }

    @Override
    public void showError(int type, String msg) {
        textView1.setText(msg + "");
    }
}