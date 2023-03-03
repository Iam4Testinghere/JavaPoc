package org.testing.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AnyMatchFunctiontest {
    /**
     * Teste folgende Methode: {@link AnyMatchFunction#checklist(List)}
     */
    @Test
    void testChecklist() {
        AnyMatchFunction anyMatchFunction = new AnyMatchFunction();
        assertThrows(IllegalArgumentException.class, () -> anyMatchFunction.checklist(new ArrayList<>()));
    }

    /**
     * Teste folgende Methode: {@link AnyMatchFunction#checklist(List)}
     */
    @Test
    void testChecklist2() {
        assertThrows(IllegalArgumentException.class, () -> (new AnyMatchFunction()).checklist(null));
    }

    /**
     * Teste folgende Methode: {@link AnyMatchFunction#checklist(List)}
     */
    @Test
    void testChecklist3() {
        AnyMatchFunction anyMatchFunction = new AnyMatchFunction();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("List is empty or contains null or empty elements");
        anyMatchFunction.checklist(stringList);
        assertTrue(anyMatchFunction.leer.isEmpty());
    }

    /**
     * Teste folgende Methode: {@link AnyMatchFunction#checklist(List)}
     */
    @Test
    void testChecklist4() {
        AnyMatchFunction anyMatchFunction = new AnyMatchFunction();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("patrick");
        stringList.add("List is empty or contains null or empty elements");
        anyMatchFunction.checklist(stringList);
        assertTrue(anyMatchFunction.leer.isEmpty());
    }

    /**
     * Teste folgende Methode: {@link AnyMatchFunction#checklist(List)}
     */
    @Test
    void testChecklist5() {
        AnyMatchFunction anyMatchFunction = new AnyMatchFunction();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(null);
        assertThrows(IllegalArgumentException.class, () -> anyMatchFunction.checklist(stringList));
    }

    /**
     * Teste folgende Methode: {@link AnyMatchFunction#checklist(List)}
     */
    @Test
    void testChecklist6() {
        AnyMatchFunction anyMatchFunction = new AnyMatchFunction();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("");
        assertThrows(IllegalArgumentException.class, () -> anyMatchFunction.checklist(stringList));
    }

    /**
     * Teste folgende Methode: {@link AnyMatchFunction#test(List)}
     */
    @Test
    void testTest() {
        AnyMatchFunction anyMatchFunction = new AnyMatchFunction();
        ArrayList<String> stringList = new ArrayList<>();
        anyMatchFunction.test(stringList);
        assertEquals(stringList, anyMatchFunction.leer);
    }

    /**
     * Teste folgende Methode: {@link AnyMatchFunction#test(List)}
     */
    @Test
    void testTest2() {
        AnyMatchFunction anyMatchFunction = new AnyMatchFunction();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        anyMatchFunction.test(stringList);
        assertTrue(anyMatchFunction.leer.isEmpty());
    }

    /**
     * Teste folgende Methode: {@link AnyMatchFunction#test(List)}
     */
    @Test
    void testTest3() {
        AnyMatchFunction anyMatchFunction = new AnyMatchFunction();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        stringList.add("foo");
        anyMatchFunction.test(stringList);
        assertTrue(anyMatchFunction.leer.isEmpty());
    }

    /**
     * Teste folgende Methode: {@link AnyMatchFunction#test(List)}
     */
    @Test
    void testTest4() {
        AnyMatchFunction anyMatchFunction = new AnyMatchFunction();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(null);
        assertThrows(IllegalArgumentException.class, () -> anyMatchFunction.test(stringList));
    }

    /**
     * Teste folgende Methode: {@link AnyMatchFunction#test(List)}
     */
    @Test
    void testTest5() {
        AnyMatchFunction anyMatchFunction = new AnyMatchFunction();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("");
        assertThrows(IllegalArgumentException.class, () -> anyMatchFunction.test(stringList));
    }
}

