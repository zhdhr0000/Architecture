package com.zhdhr0000.architecture.utils;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhangyh on 2017/2/28.
 * RxJava2工具类
 */

public class RxUtil {

    /**
     * 通过create方法创建一个Observable对象,并且进行默认的IO控制.
     * @param t
     * @param <T>
     * @return Observable<T>
     */
    public static <T> Observable<T> init(final T t){
        return create(t).compose(RxUtil.<T>schedulerHelper());
    }

    /**
     * 通过create方法创建一个Observable对象,直接在subscribe的时候即可处理异常,也可以通过doOnError统一处理来处理异常
     * @param t
     * @param <T>
     * @return Observable<T>
     */
    public static <T> Observable<T> create(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                emitter.onNext(t);
                emitter.onComplete();
            }
        });
    }


    /**
     * 通过new,创建一个Observable对象.和create创建出来的区别还不造...
     * @param t
     * @param <T>
     * @return Observable<T>
     */
    public static <T> Observable<T> newSource(final T t) {
        return new Observable<T>() {
            @Override
            protected void subscribeActual(Observer<? super T> observer) {
                try {
                    observer.onNext(t);
                    observer.onComplete();
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        };
    }

    //placeholder 未经测试的代码
    public static <T> Flowable<T> create(final T t, BackpressureStrategy strategy) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                emitter.onNext(t);
                emitter.onComplete();
            }
        }, strategy);
    }

    /**
     * 简单的线程调度处理,在IO线程处理subscribe,在主线程处理observe
     * @param <T>
     * @return Observable<T>
     */
    public static <T> ObservableTransformer<T, T> schedulerHelper() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
