package com.zhdhr0000.architecture.base;

/**
 * Created by zhangyh on 2017/2/28.
 * View的基础抽象协议
 */

public interface IView {
    void showError(int type,String msg);
    void showToast(String toast);
}
