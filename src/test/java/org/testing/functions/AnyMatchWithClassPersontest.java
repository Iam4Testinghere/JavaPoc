package org.testing.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AnyMatchWithClassPersontest {
    /**
     * Method under test: {@link AnyMatchWithClassPerson#personListSort(List)}
     */
    @Test
    void testPersonListSort() {
        AnyMatchWithClassPerson anyMatchWithClassPerson = new AnyMatchWithClassPerson();
        assertTrue(anyMatchWithClassPerson.personListSort(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link AnyMatchWithClassPerson#personListSort(List)}
     */
    @Test
    void testPersonListSort2() {
        AnyMatchWithClassPerson anyMatchWithClassPerson = new AnyMatchWithClassPerson();

        ArrayList<AnyMatchWithClassPerson.Person> personList = new ArrayList<>();
        personList.add((new AnyMatchWithClassPerson()).new Person("Name", 1, 1));
        assertEquals(1, anyMatchWithClassPerson.personListSort(personList).size());
    }

    /**
     * Method under test: {@link AnyMatchWithClassPerson#personListSort(List)}
     */
    @Test
    void testPersonListSort3() {
        AnyMatchWithClassPerson anyMatchWithClassPerson = new AnyMatchWithClassPerson();

        ArrayList<AnyMatchWithClassPerson.Person> personList = new ArrayList<>();
        personList.add(null);
        personList.add((new AnyMatchWithClassPerson()).new Person("Name", 1, 1));
        assertThrows(IllegalArgumentException.class, () -> anyMatchWithClassPerson.personListSort(personList));
    }

    /**
     * Method under test: {@link AnyMatchWithClassPerson#personListSort(List)}
     */
    @Test
    void testPersonListSort4() {
        AnyMatchWithClassPerson anyMatchWithClassPerson = new AnyMatchWithClassPerson();

        ArrayList<AnyMatchWithClassPerson.Person> personList = new ArrayList<>();
        personList.add((new AnyMatchWithClassPerson()).new Person("Name", 1, 1));
        personList.add((new AnyMatchWithClassPerson()).new Person(null, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> anyMatchWithClassPerson.personListSort(personList));
    }

    /**
     * Method under test: {@link AnyMatchWithClassPerson#personListSort(List)}
     */
    @Test
    void testPersonListSort5() {
        AnyMatchWithClassPerson anyMatchWithClassPerson = new AnyMatchWithClassPerson();

        ArrayList<AnyMatchWithClassPerson.Person> personList = new ArrayList<>();
        personList.add((new AnyMatchWithClassPerson()).new Person("Name", 1, 1));
        personList.add((new AnyMatchWithClassPerson()).new Person("", 1, 1));
        assertThrows(IllegalArgumentException.class, () -> anyMatchWithClassPerson.personListSort(personList));
    }
}

