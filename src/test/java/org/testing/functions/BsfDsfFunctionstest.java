package org.testing.functions;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class BsfDsfFunctionstest {
    /**
     * Method under test: {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     */
    @Test
    void testSearchPersonDFS() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Name", 1);
        assertThrows(IllegalArgumentException.class, () -> bsfDsfFunctions.searchPersonDFS("Name", 1, new ArrayList<>()));
    }

    /**
     * Method under test: {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     */
    @Test
    void testSearchPersonDFS2() {
        assertThrows(IllegalArgumentException.class,
                () -> (new BsfDsfFunctions("Name", 1)).searchPersonDFS(null, 0, null));
    }

    /**
     * Method under test: {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     */
    @Test
    void testSearchPersonDFS3() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Name", 1);
        assertThrows(IllegalArgumentException.class, () -> bsfDsfFunctions.searchPersonDFS("", 1, new ArrayList<>()));
    }

    /**
     * Method under test: {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     */
    @Test
    void testSearchPersonDFS4() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Name", 1);
        assertThrows(IllegalArgumentException.class,
                () -> bsfDsfFunctions.searchPersonDFS("Name", -1, new ArrayList<>()));
    }

    /**
     * Method under test: {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     */
    @Test
    void testSearchPersonDFS5() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Name", 1);

        ArrayList<BsfDsfFunctions> bsfDsfFunctionsList = new ArrayList<>();
        BsfDsfFunctions bsfDsfFunctions1 = new BsfDsfFunctions("Name", 1);

        bsfDsfFunctionsList.add(bsfDsfFunctions1);
        assertSame(bsfDsfFunctions1, bsfDsfFunctions.searchPersonDFS("Name", 1, bsfDsfFunctionsList));
    }

    /**
     * Method under test: {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSearchPersonDFS6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.testing.functions.BsfDsfFunctions.searchPersonDFS(BsfDsfFunctions.java:58)
        //   See https://diff.blue/R013 to resolve this issue.

        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Name", 1);

        ArrayList<BsfDsfFunctions> bsfDsfFunctionsList = new ArrayList<>();
        bsfDsfFunctionsList.add(new BsfDsfFunctions(null, 1));
        bsfDsfFunctions.searchPersonDFS("Name", 1, bsfDsfFunctionsList);
    }

    /**
     * Method under test: {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     */
    @Test
    void testSearchPersonDFS7() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Name", 1);

        ArrayList<BsfDsfFunctions> bsfDsfFunctionsList = new ArrayList<>();
        bsfDsfFunctionsList.add(new BsfDsfFunctions("", 1));
        assertThrows(IllegalArgumentException.class,
                () -> bsfDsfFunctions.searchPersonDFS("Name", 1, bsfDsfFunctionsList));
    }

    /**
     * Method under test: {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     */
    @Test
    void testSearchPersonDFS8() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Name", 1);

        ArrayList<BsfDsfFunctions> bsfDsfFunctionsList = new ArrayList<>();
        bsfDsfFunctionsList.add(new BsfDsfFunctions("Name", 0));
        assertThrows(IllegalArgumentException.class,
                () -> bsfDsfFunctions.searchPersonDFS("Name", 1, bsfDsfFunctionsList));
    }

    /**
     * Method under test: {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     */
    @Test
    void testSearchPersonDFS9() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Name", 1);

        ArrayList<BsfDsfFunctions> bsfDsfFunctionsList = new ArrayList<>();
        bsfDsfFunctionsList.add(null);
        assertThrows(IllegalArgumentException.class,
                () -> bsfDsfFunctions.searchPersonDFS("Name", 1, bsfDsfFunctionsList));
    }
}

