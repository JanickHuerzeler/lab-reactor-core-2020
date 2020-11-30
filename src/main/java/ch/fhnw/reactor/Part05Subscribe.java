package ch.fhnw.reactor;

import reactor.core.publisher.Flux;

public class Part05Subscribe {

    /**
     * GENERAL COMMENT
     *
     * The tests provided for this class are not very helpful, i.e. they only test whether the
     * expected Flux is returned (one which emits 1,2,3); but your task is to register different
     * subscribers on that Flux before returning the result, i.e. you can observe the expected
     * behavior of your subscriptions in the console window.
     */

    /**
     * TODO 05.01
     *
     * <p>
     * Return a Flux which emits: 1, 2, 3 integers and then completes.
     * <p>
     * Subscribe to this Flux without any Consumer. Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeEmpty() {
    	 Flux<Integer> flux = Flux.just(1, 2, 3);
         // TODO add the registration code here such that the elements are printed in the console
    	 //      as a side effect of executing the JUnit test.
         return flux;
        }

    /**
     * TODO 05.02
     *
     * <p>
     * Return a Flux which emits: 1, 2, 3 integers and then completes
     * <p>
     * Subscribe to this Flux using a Consumer, which prints out the elements. Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithConsumer() {
        return null;
    }

    /**
     * TODO 05.03
     *
     * <p>
     * Return a Flux which emits: 1, 2, 3 integers and then completes
     * <p>
     * Subscribe to this Flux using a Consumer (which prints out the elements) and complete Consumer
     * (which prints out "completed"). Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithConsumerAndCompleteConsumer() {
        return null;
    }


    /**
     * TODO 05.04
     * <p>
     * Return a Flux which emits: 1, 2, 3 integers and then signals error.
     * <p>
     * Subscribe to this Flux using a Consumer (which prints out the elements) and error Consumer
     * (which logs the error message).
     * <p>
     * Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithConsumerAndErrorConsumer() {
        return null;
    }

    /**
     * TODO 05.05
     * <p>
     * Return a Flux which emits: 1, 2, 3 integers and then completes.
     * <p>
     * Subscribe to this Flux using a Subscription Consumer requesting only 2 elements.
     * <p>
     * Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithSubscriptionConsumer() {
        return null;
    }

    /**
     * TODO 05.06
     * <p>
     * Return a Flux which emits 1, 2, 3 and then completes.
     * <p>
     * Subscribe to this Flux using a Subscriber requesting in onSubscribe 1 element and then later as much as
     * the current element it was. Inspect the logs using the 'log' operator.
     */
    public Flux<Integer> subscribeWithSubscription() {
        return null;
    }

}