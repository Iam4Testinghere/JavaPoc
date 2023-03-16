package tutorial.code.snipes.algorithmen;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BsfDsfFunctions {
    private int age;
    private List<BsfDsfFunctions> list;
    private String name;
    public BsfDsfFunctions() {
    }
    public BsfDsfFunctions(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public List<BsfDsfFunctions> getList() {
        return list;
    }
    public void setList(List<BsfDsfFunctions> list) {
        this.list = list;
    }
    // Funktion searchPersonDFS mit Tiefensuche erstellen
    public BsfDsfFunctions searchPersonDFS(String name, int age, List<BsfDsfFunctions> list) {
        if (name == null || name.isEmpty() || age < 0 || list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingangsparameter");
        }
        for (BsfDsfFunctions person : list) {
            if (person == null) {
                throw new IllegalArgumentException("Test-Objekt in der Liste darf nicht null sein.");
            }
            if (person.getName().equals(name) && person.getAge() == age) {
                return person;
            }
            BsfDsfFunctions result = searchPersonDFS(name, age, person.getList());
            if (result != null) {
                return result;
            }
        }
        return null;
    }
    // Funktion searchPersonBSF mit Breitensuche erstellen
    public BsfDsfFunctions searchPersonBSF(String name, int age, List<BsfDsfFunctions> list) {
        // check null and empty
        if (name == null || name.isEmpty() || age < 0 || list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingangsparameter");
        }
        Queue<BsfDsfFunctions> queue = new LinkedList<>();
        queue.addAll(list);
        while (!queue.isEmpty()) {
            BsfDsfFunctions person = queue.remove();
            if (person == null) {
                throw new IllegalArgumentException("Test-Objekt in der Liste darf nicht null sein.");
            }
            if (person.getName().equals(name) && person.getAge() == age) {
                return person;
            }
            if (person.getList() != null && !person.getList().isEmpty()) {
                queue.addAll(person.getList());
            }
        }
        return null;
    }
}