package tutorial.code.snipes.FunctionalProgramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class FunctionalProgrammingTest {
    // Testet, ob eine leere Liste "false" zurückgibt
    @Test
    void testLeereListe() {
        FunctionalProgramming functionalProgramming = new FunctionalProgramming("Max Muster", 1);
        assertFalse(functionalProgramming.isPersonInList("John Doe", new ArrayList<>()));
    }
    // Testet, ob "null" als Parameter "false" zurückgibt
    @Test
    void testNullParameter() {
        assertFalse((new FunctionalProgramming("Max Muster", 1)).isPersonInList(null, null));
    }
    // Testet, ob "null" als Liste "false" zurückgibt
    @Test
    void testNullListe() {
        assertFalse((new FunctionalProgramming("Max Muster", 1)).isPersonInList("foo", null));
    }
    // Testet, ob ein leerer String "false" zurückgibt
    @Test
    void testLeererString() {
        FunctionalProgramming functionalProgramming = new FunctionalProgramming("Max Muster", 1);
        assertFalse(functionalProgramming.isPersonInList("", new ArrayList<>()));
    }
    // Testet, ob eine Liste mit einer anderen Person "false" zurückgibt
    @Test
    void testListeMitAndererPerson() {
        FunctionalProgramming functionalProgramming = new FunctionalProgramming("Max Muster", 1);
        ArrayList<FunctionalProgramming> functionalProgrammingList = new ArrayList<>();
        functionalProgrammingList.add(new FunctionalProgramming("Max Muster", 1));
        assertFalse(functionalProgramming.isPersonInList("John Doe", functionalProgrammingList));
    }
    // Testet, ob eine Liste mit zwei anderen Personen "false" zurückgibt
    @Test
    void testListeMitZweiAnderenPersonen() {
        FunctionalProgramming functionalProgramming = new FunctionalProgramming("Max Muster", 1);
        ArrayList<FunctionalProgramming> functionalProgrammingList = new ArrayList<>();
        functionalProgrammingList.add(new FunctionalProgramming("Max Muster", 1));
        functionalProgrammingList.add(new FunctionalProgramming("Max Muster", 1));
        assertFalse(functionalProgramming.isPersonInList("John Doe", functionalProgrammingList));
    }
    // Testet, ob eine Liste mit der gesuchten Person "true" zurückgibt
    @Test
    void testListeMitGesuchterPerson() {
        FunctionalProgramming functionalProgramming = new FunctionalProgramming("Max Muster", 1);
        ArrayList<FunctionalProgramming> functionalProgrammingList = new ArrayList<>();
        functionalProgrammingList.add(new FunctionalProgramming("John Doe", 1));
        assertTrue(functionalProgramming.isPersonInList("John Doe", functionalProgrammingList));
    }
    // Testet, ob eine Liste mit einer Person ohne Namen "false" zurückgibt
    @Test
    void testListeMitPersonOhneNamen() {
        FunctionalProgramming functionalProgramming = new FunctionalProgramming("Max Muster", 1);
        ArrayList<FunctionalProgramming> functionalProgrammingList = new ArrayList<>();
        functionalProgrammingList.add(new FunctionalProgramming(null, 1));
        assertFalse(functionalProgramming.isPersonInList("John Doe", functionalProgrammingList));
    }
}