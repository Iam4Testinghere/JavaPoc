package tutorial.code.snipes.algorithmen;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BsfDsfFunctionsTest {
    /**
     * Testet die Methode {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     * mit einem vorhandenen Namen, einem gültigen Level und einer Liste von Funktionen.
     * Erwartet wird, dass die Funktion die richtige Person zurückgibt.
     */
    @Test
    void testSearchPersonDFS_validInput_returnsPerson() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Max Muster", 1);
        ArrayList<BsfDsfFunctions> bsfDsfFunctionsList = new ArrayList<>();
        BsfDsfFunctions bsfDsfFunctions1 = new BsfDsfFunctions("Max Muster", 1);
        bsfDsfFunctionsList.add(bsfDsfFunctions1);
        assertSame(bsfDsfFunctions1, bsfDsfFunctions.searchPersonDFS("Max Muster", 1, bsfDsfFunctionsList));
    }
    /**
     * Testet die Methode {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     * mit einem ungültigen Max Mustern.
     * Erwartet wird, dass eine IllegalArgumentException ausgelöst wird.
     */
    @Test
    void testSearchPersonDFS_invalidName_throwsIllegalArgumentException() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Max Muster", 1);
        assertThrows(IllegalArgumentException.class, () -> bsfDsfFunctions.searchPersonDFS(null, 0, null));
    }
    /**
     * Testet die Methode {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     * mit einem leeren Namen.
     * Erwartet wird, dass eine IllegalArgumentException ausgelöst wird.
     */
    @Test
    void testSearchPersonDFS_emptyName_throwsIllegalArgumentException() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Max Muster", 1);
        assertThrows(IllegalArgumentException.class, () -> bsfDsfFunctions.searchPersonDFS("", 1, new ArrayList<>()));
    }
    /**
     * Testet die Methode {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     * mit einem ungültigen Level.
     * Erwartet wird, dass eine IllegalArgumentException ausgelöst wird.
     */
    @Test
    void testSearchPersonDFS_invalidLevel_throwsIllegalArgumentException() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Max Muster", 1);
        assertThrows(IllegalArgumentException.class, () -> bsfDsfFunctions.searchPersonDFS("Max Muster", -1, new ArrayList<>()));
    }
    /**
     * Testet die Methode {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     * mit einer Funktion in der Liste, die den gleichen Namen hat, aber einen anderen Level.
     * Erwartet wird, dass eine IllegalArgumentException ausgelöst wird.
     */
    @Test
    void testSearchPersonDFS_functionWithSameNameButDifferentLevel_throwsIllegalArgumentException() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Max Muster", 1);
        ArrayList<BsfDsfFunctions> bsfDsfFunctionsList = new ArrayList<>();
        bsfDsfFunctionsList.add(new BsfDsfFunctions("Max Muster", 0));
        assertThrows(IllegalArgumentException.class, () -> bsfDsfFunctions.searchPersonDFS("Max Muster", 1, bsfDsfFunctionsList));
    }
    /**
     * Testet die Methode {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     * mit einer leeren Funktion in der Liste.
     * Erwartet wird, dass eine IllegalArgumentException ausgelöst wird.
     */
    @Test
    void testSearchPersonDFS_emptyFunctionInList_throwsIllegalArgumentException() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Max Muster", 1);
        ArrayList<BsfDsfFunctions> bsfDsfFunctionsList = new ArrayList<>();
        bsfDsfFunctionsList.add(new BsfDsfFunctions("", 1));
        assertThrows(IllegalArgumentException.class, () -> bsfDsfFunctions.searchPersonDFS("Max Muster", 1, bsfDsfFunctionsList));
    }
    /**
     * Testet die Methode {@link BsfDsfFunctions#searchPersonDFS(String, int, List)}
     * mit einer ungültigen Funktion in der Liste.
     * Erwartet wird, dass eine IllegalArgumentException ausgelöst wird.
     */
    @Test
    void testSearchPersonDFS_invalidFunctionInList_throwsIllegalArgumentException() {
        BsfDsfFunctions bsfDsfFunctions = new BsfDsfFunctions("Max Muster", 1);
        ArrayList<BsfDsfFunctions> bsfDsfFunctionsList = new ArrayList<>();
        bsfDsfFunctionsList.add(null);
        assertThrows(IllegalArgumentException.class, () -> bsfDsfFunctions.searchPersonDFS("Max Muster", 1, bsfDsfFunctionsList));
    }
}