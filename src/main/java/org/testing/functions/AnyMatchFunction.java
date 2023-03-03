package org.testing.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnyMatchFunction {

    List<String> leer = new ArrayList<>();
    public AnyMatchFunction() {}

    public void checklist(List<String> leer) {
        if (leer == null || leer.isEmpty() || leer.stream().anyMatch(s -> s == null || s.isEmpty())) {
            throw new IllegalArgumentException("List is empty or contains null or empty elements");
        }
        leer.stream().filter( f -> f.equals("patrick")).collect(Collectors.toList());
    }

}

