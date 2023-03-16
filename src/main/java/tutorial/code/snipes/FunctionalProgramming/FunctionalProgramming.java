package tutorial.code.snipes.FunctionalProgramming;

import tutorial.code.snipes.Object.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FunctionalProgramming {
    String personVorname; // Benenne die Variablen gemäß der Java-Übung
    int ageOfPerson;
    List<FunctionalProgramming> personExists = new ArrayList<>(); // Initialisiere die Liste
    public FunctionalProgramming(String personVorname, int ageOfPerson) {
        this.personVorname = personVorname;
        this.ageOfPerson = ageOfPerson;
    }
    public FunctionalProgramming() {}
    List<FunctionalProgramming> personList = new ArrayList<>();
    public String getPersonVorname() {
        return personVorname;
    }
    public void setPersonVorname(String personVorname) {
        this.personVorname = personVorname;
    }
    public int getAgeOfPerson() {
        return ageOfPerson;
    }
    public void setAgeOfPerson(int ageOfPerson) {
        this.ageOfPerson = ageOfPerson;
    }
    /**
     * Überprüft, ob eine Person in der Liste vorhanden ist.
     * Verwendet Guard Clauses, um die Lesbarkeit zu verbessern.
     */
    public boolean isPersonInList(String findPerson, List<FunctionalProgramming> personList) {
        if (findPerson == null || findPerson.trim().isEmpty() || personList == null || personList.isEmpty()) {
            return false;
        }
        return personList.stream()
                .anyMatch(person -> person != null && person.getPersonVorname() != null && person.getPersonVorname().equals(findPerson));
    }
    /**
     * Filtert die Personenliste nach Vorname und Alter.
     * Verwendet die Lambda-Schreibweise für die Filterfunktion.
     */
    public List<FunctionalProgramming> filterPersonList(String firstName, int age, List<FunctionalProgramming> personList) {
        return personList.stream()
                .filter(person -> person.getPersonVorname().equals(firstName) && person.getAgeOfPerson() == age)
                .collect(Collectors.toList());
    }
    /**
     * Testet die Verwendung von Consumer, Runnable, Supplier und Optional-Klassen.
     * Verwendet die Methode orElse() anstelle von ifPresent().
     */
    public void consumerRunnableSupplierOptional() {
        Consumer<String> c = s -> {
            String sw = "hallo"; // Entferne die Verwendung von new String()     };
            Runnable r1 = () -> {
                new String(); // Entferne die Instanziierung von Objekten ohne Verwendung
                new Integer(1);
            };
            Supplier<Integer> sup = () -> 1;
            Supplier<Person> p = () -> new Person("om", 1);
            Function<String, Boolean> f = String::isEmpty; // Verwendet Methodenreferenz anstelle von Lambda
            Optional<String> optional = Optional.ofNullable(null);
            optional.orElse(""); // Verwendet orElse() anstelle von ifPresent()
        };
    }
}
