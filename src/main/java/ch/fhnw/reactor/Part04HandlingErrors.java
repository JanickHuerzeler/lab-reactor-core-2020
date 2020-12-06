package ch.fhnw.reactor;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive Streams specification specifies that errors are terminal events.
 */
@Slf4j
public class Part04HandlingErrors {

    /**
     * 04.01
     * <p>
     * Map the received flux into numbers using {@link Integer#parseInt} and observe
     * that in case of {@link NumberFormatException} the flux is terminated with
     * error. Use the log operator to view that the flux is terminated with an
     * error.
     */
    public Flux<Integer> errorIsTerminal(Flux<String> numbers) {
        return numbers.log().map(Integer::parseInt).log();
    }

    /**
     * 04.02
     * <p>
     * Map the received flux into numbers using {@link Integer#parseInt} and provide
     * a fallback of 0 in case of {@link NumberFormatException}. Use the log
     * operator to view that the flux is terminated successfully.
     */
    public Flux<Integer> handleErrorWithFallback(Flux<String> numbers) {
        return numbers.map(Integer::parseInt).onErrorReturn(0);
    }

    /**
     * 04.03
     * <p>
     * Map the received flux into numbers using {@link Integer#parseInt} and provide
     * a fallback of 0 in case of {@link NumberFormatException} and continue with
     * other items.
     * <p>
     * Use the flatMap and check where you put the onErrorReturn operator
     */
    public Flux<Integer> handleErrorAndContinue(Flux<String> numbers) {
        return numbers.map(str -> {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException ex) {
                return 0;
            }
        });
    }

    /**
     * 04.04
     * <p>
     * Map the received flux into numbers using {@link Integer#parseInt} and provide
     * an empty Mono when {@link NumberFormatException} is occurred and continue
     * with other items.
     */
    public Flux<Integer> handleErrorWithEmptyMonoAndContinue(Flux<String> numbers) {
        return numbers.map(Integer::parseInt).onErrorContinue(e -> e instanceof NumberFormatException, (t, o) -> {});
    }

    /**
     * TODO 04.05
     * <p>
     * For each item call received in colors flux call the
     * {@link #simulateRemoteCall} operation. Timeout in case the
     * {@link #simulateRemoteCall} does not return within 400 ms, but retry twice If
     * still no response then provide "default" as a return value
     */
    public Flux<String> timeOutWithRetry(Flux<String> colors) {
        //return colors.map(col -> simulateRemoteCall(col).time).timeout(Duration.ofMillis(400));
    }

    public Mono<String> simulateRemoteCall(String input) {
        int delay = input.length() * 100;
        return Mono.just(input).doOnNext(s -> log.info("Received {} delaying for {} ", s, delay))
                .map(i -> "processed " + i).delayElement(Duration.of(delay, ChronoUnit.MILLIS));
    }

}
