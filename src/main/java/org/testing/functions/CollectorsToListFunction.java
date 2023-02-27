package org.testing.functions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsToListFunction {

    public CollectorsToListFunction(){};


    public boolean test4() {
        Stream<String> st = Stream.of("d", "Bc");
        for (String s : st.collect(Collectors.toList())) {
            try {
                TestIfOK.valueOf(s);
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return true;
    }

    enum  TestIfOK{
        A,B
    };
    public boolean test5() {
        Stream<String> st = Stream.of("Ac","B");
        st.map( m ->{
            return TestIfOK.valueOf(m);

        });
     return true;
    }
    public static void main(String[] args) {

//  new CheckCodeParts().test3();
        System.out.println( new CollectorsToListFunction().test5());
    }
}
