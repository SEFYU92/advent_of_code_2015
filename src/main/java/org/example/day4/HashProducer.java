package org.example.day4;

import org.apache.commons.codec.digest.DigestUtils;

public class HashProducer {
    public static String produceHash(String input) {
        var tail = 0;
        var solution = "";
        while (isNotCompliant(solution)) {
            String key = input + (++tail);
            solution = DigestUtils.md5Hex(key);
        }
        return String.valueOf(tail);
    }

    public static boolean isNotCompliant(String solution) {
        return !(solution.startsWith("00000") && Character.isDigit(solution.charAt(5)));
    }
}
