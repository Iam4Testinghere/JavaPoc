package tutorial.code.snipes.FunctionalProgramming;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnyMatchUsingPerson {

    static class Person {
        private String name;
        private int age;
        private int bod;
        public Person(String name, int age, int bod) {
            this.name = name;
            this.age = age;
            this.bod = bod;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
        public int getBod() {
            return bod;
        }
        /**
         * Überschreibt die toString() Methode für eine benutzerdefinierte Ausgabe.
         */
        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", bod=" + bod +
                    '}';
        }
    }
    /**
     * Sortiert die Liste der Personen anhand des Namens und des Alters.
     * Überprüft die Eingangsparameter mit Guard Clauses.
     */
    List<Person> personListSortNamensAge(List<Person> list) {
        if (list == null || list.isEmpty() || list.stream()
                .anyMatch(p -> p == null || p.getName() == null || p.getName().trim().isEmpty() || p.getAge() < 0 || p.getBod() < 0)) {
            throw new IllegalArgumentException("Ungültige Eingangsparameter");
        }
        return list.stream()
                .filter(Objects::nonNull) // Entfernt null-Objekte aus der Liste
                .sorted(Comparator.comparing(Person::getName).thenComparing(Person::getAge))
                .collect(Collectors.toList());
    }
    /**
     * Filtert die Liste der Personen anhand des Namens.
     * Überprüft die Eingangsparameter mit Guard Clauses.
     */
    List<Person> personListSortNamensOnly(List<Person> list, String name) {
        if (list == null || list.isEmpty() || name == null || name.trim().isEmpty() || list.stream()
                .anyMatch(p -> p == null || p.getName() == null || p.getName().trim().isEmpty() || p.getAge() < 0 || p.getBod() < 0)) {
            throw new IllegalArgumentException("Ungültige Eingangsparameter");
        }
        return list.stream()
                .filter(Objects::nonNull) // Entfernt null-Objekte aus der Liste
                .filter(p -> p.getName().equals(name))
                .collect(Collectors.toList());
    }
}
