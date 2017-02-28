package com.zhdhr0000.architecture.utils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

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
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhangyh on 2017/2/28.
 */

public class RxUtil {

    public static <T> Observable<T> createData(final T t, final Disposable disposable) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.setDisposable(disposable);
                    emitter.onNext(t);
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }
            }
        });
    }

    public static <T> Observable<T> unsafeCreateData(final T t, final Disposable d){
        return Observable.unsafeCreate(new ObservableSource<T>() {
            @Override
            public void subscribe(Observer<? super T> observer) {
                try {
                    observer.onSubscribe(d);
                    observer.onNext(t);
                    observer.onComplete();
                }catch (Exception e){
                    observer.onError(e);
                }
            }
        });
    }

    public static <T> Flowable<T> createData(final T t, final Disposable disposable, BackpressureStrategy strategy){
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.setDisposable(disposable);
                    emitter.onNext(t);
                    emitter.onComplete();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },strategy);
    }

    public static <T> Flowable unsafeCreateData(final T t,final Subscription subscription){
        return Flowable.unsafeCreate(new Flowable() {
            @Override
            protected void subscribeActual(Subscriber s) {
                try {
                    s.onSubscribe(subscription);
                    s.onNext(t);
                    s.onComplete();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static <T>ObservableTransformer<T,T> schedulerHelper(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
