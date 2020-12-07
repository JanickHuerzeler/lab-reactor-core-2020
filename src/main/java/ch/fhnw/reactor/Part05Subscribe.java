package ch.fhnw.reactor;

import java.util.function.Consumer;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Part05Subscribe {

    /**
     * GENERAL COMMENT
     *
     * The tests provided for this class are not very helpful, i.e. they only test
     * whether the expected Flux is returned (one which emits 1,2,3); but your task
     * is to register different subscribers on that Flux before returning the
     * result, i.e. you can observe the expected behavior of your subscriptions in
     * the console window.
     */

    /**
     * 05.01
     *
     * <p>
     * Return a Flux which emits: 1, 2, 3 integers and then completes.
     * <p>
     * Subscribe to this Flux without any Consumer. Inspect the logs by using the
     * 'log' operator.
     */
    public Flux<Integer> subscribeEmpty() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        // add the registration code here such that the elements are printed in the
        // console
        // as a side effect of executing the JUnit test.
        flux.log().subscribe();
        return flux;
    }

    /**
     * 05.02
     *
     * <p>
     * Return a Flux which emits: 1, 2, 3 integers and then completes
     * <p>
     * Subscribe to this Flux using a Consumer, which prints out the elements.
     * Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithConsumer() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        flux.log().subscribe(i -> log.info("{}", i));
        return flux;
    }

    /**
     * 05.03
     *
     * <p>
     * Return a Flux which emits: 1, 2, 3 integers and then completes
     * <p>
     * Subscribe to this Flux using a Consumer (which prints out the elements) and
     * complete Consumer (which prints out "completed"). Inspect the logs by using
     * the 'log' operator.
     */
    public Flux<Integer> subscribeWithConsumerAndCompleteConsumer() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        flux.log().subscribe(i -> log.info("{}", i), null, () -> log.info("completed"));
        return flux;
    }

    /**
     * 05.04
     * <p>
     * Return a Flux which emits: 1, 2, 3 integers and then signals error.
     * <p>
     * Subscribe to this Flux using a Consumer (which prints out the elements) and
     * error Consumer (which logs the error message).
     * <p>
     * Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithConsumerAndErrorConsumer() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4).map(i -> {
            if (i != 4)
                return i;
            else
                throw new IllegalStateException("error");
        });

        flux.log().subscribe(i -> log.info("{}", i), t -> log.info("{}", t.getMessage()));
        return flux;
    }

    /**
     * 05.05
     * <p>
     * Return a Flux which emits: 1, 2, 3 integers and then completes.
     * <p>
     * Subscribe to this Flux using a Subscription Consumer requesting only 2
     * elements.
     * <p>
     * Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithSubscriptionConsumer() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        //flux.log().take(2).subscribe();
        flux.log().subscribe(null, null, null, new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) {
                subscription.request(2);
            }
        });
        return flux;
    }

    /**
     * TODO 05.06
     * <p>
     * Return a Flux which emits 1, 2, 3 and then completes.
     * <p>
     * Subscribe to this Flux using a Subscriber requesting in onSubscribe 1 element
     * and then later as much as the current element it was. Inspect the logs using
     * the 'log' operator.
     */
    public Flux<Integer> subscribeWithSubscription() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        flux.log().subscribe(new Subscriber<Integer>() {

            Subscription s;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                s.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                s.request(integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
        return flux;
    }

}