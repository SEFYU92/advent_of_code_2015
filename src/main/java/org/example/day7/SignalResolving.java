package org.example.day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SignalResolving {
    public static HashMap<String, String> resolveSignal(List<String> daySevenInput) {
        var signalMap = new HashMap<String, String>();
        daySevenInput.forEach(x->{
            var noArrowLine = x.replace("-> ","");
            var terms = noArrowLine.split(" ");
            signalMap.put(terms[terms.length - 1], String.join(" ", Arrays.copyOfRange(terms,0,terms.length-1)));
        });
        return signalMap;
    }
}
