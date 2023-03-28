package org.example.day4;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class HashProducer {
    private final String headingZeros;

    public HashProducer(String headingZeros) {
        this.headingZeros = headingZeros;
    }

    public String produceHash(String input) {
        return produceHash(input, 0, 1);
    }

    public String produceHash(String input, int tailStart, int step) {
        var tail = tailStart;
        var solution = "";
        while (isNotCompliant(solution)) {
            tail += step;
            String key = input + tail;
            solution = DigestUtils.md5Hex(key);
        }
        return String.valueOf(tail);
    }

    public String produceConcurrentHash(String input) {
        CompletableFuture<?> combinedFutures = null;
        try {
            combinedFutures = CompletableFuture.anyOf(IntStream.range(0, 8)
                    .mapToObj(i -> CompletableFuture.supplyAsync(() -> produceHash(input, i, 8)))
                    .toList().toArray(new CompletableFuture[0]));
            return (String) combinedFutures.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            Optional.ofNullable(combinedFutures).ifPresent(f -> f.cancel(true));
        }
    }

    private boolean isNotCompliant(String solution) {
        return !solution.startsWith(headingZeros);
    }
}
