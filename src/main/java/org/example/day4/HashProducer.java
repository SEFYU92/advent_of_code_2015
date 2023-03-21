package org.example.day4;

import org.apache.commons.codec.digest.DigestUtils;

public class HashProducer {
    private final String headingZeros;

    public HashProducer(String headingZeros) {
        this.headingZeros = headingZeros;
    }

    public String produceHash(String input) {
        var tail = 0;
        var solution = "";
        while (isNotCompliant(solution)) {
            String key = input + (++tail);
            solution = DigestUtils.md5Hex(key);
        }
        return String.valueOf(tail);
    }

    private boolean isNotCompliant(String solution) {
        return !solution.startsWith(headingZeros);
    }
}
