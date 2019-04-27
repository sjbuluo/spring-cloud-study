package com.sun.health.consumer.base.command;

import com.netflix.hystrix.HystrixObservableCommand;
import com.sun.health.consumer.base.entity.User;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

public class UserObservableCommand extends HystrixObservableCommand<User> {
    private RestTemplate restTemplate;
    private int id;

    public UserObservableCommand(Setter setter, RestTemplate restTemplate, int id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected Observable<User> construct() {
        return Observable.unsafeCreate(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {
                    if(!subscriber.isUnsubscribed()) {
//                        User user = restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
                        User user = restTemplate.getForObject("http://HELLO-SERVICE/hello/users/{1}", User.class, id);
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }
                } catch (RestClientException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
}
