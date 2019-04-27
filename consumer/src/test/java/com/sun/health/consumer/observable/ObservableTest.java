package com.sun.health.consumer.observable;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

public class ObservableTest {

    @Test
    public void test1() {
        Observable<String> stringObservable = Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("HelloRxJava");
                subscriber.onNext("I am sj");
                subscriber.onCompleted();
            }
        });
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        };
        stringObservable.subscribe(subscriber);
        stringObservable.subscribe(subscriber);
        stringObservable.subscribe(subscriber);
        stringObservable.subscribe(subscriber);
        stringObservable.subscribe(subscriber);
    }

}
