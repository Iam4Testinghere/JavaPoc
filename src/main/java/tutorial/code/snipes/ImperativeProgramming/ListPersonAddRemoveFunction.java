package tutorial.code.snipes.ImperativeProgramming;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListPersonAddRemoveFunction {
    public List<ListPersonAddRemoveFunction> listPerson = new ArrayList<>();
    private String name;
    private String vorname;
    public ListPersonAddRemoveFunction() {
    }
    public ListPersonAddRemoveFunction(String name, String vorname) {
        this.name = name;
        this.vorname = vorname;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return " name=" + name + ", vorname=" + vorname + "]";
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVorname() {
        return vorname;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    /**
     * Methode zum Entfernen einer Person aus der Liste.
     * Die Person wird anhand von Vorname und Name gefunden und entfernt.
     */
    public void remove(String vorname, String name) {
        Iterator<ListPersonAddRemoveFunction> it = listPerson.iterator();
        while (it.hasNext()) {
            ListPersonAddRemoveFunction p = it.next();
            if (p.getVorname().equals(vorname) && p.getName().equals(name)) {
                it.remove();
            }
        }
    }
    /**
     * Methode zum Entfernen einer Person aus der Liste.
     * Die Person wird anhand von Vorname und Name gefunden und entfernt.
     * Diese Methode gibt true zurück, wenn die Person gefunden und entfernt wurde, andernfalls false.
     */
    public boolean removePerson(String name, String vorname) {
        for (int i = 0; i < listPerson.size(); i++) {
            ListPersonAddRemoveFunction person = listPerson.get(i);
            if (person.getName().equals(name) && person.getVorname().equals(vorname)) {
                listPerson.remove(i);
                return true;
            }
        }
        return false;
    }
     public List<ListPersonAddRemoveFunction> getListPerson() {
        return listPerson;
    }
    /**
     * Methode zum Hinzufügen einer neuen Person zur Liste.
     * Wenn bereits eine Person mit demselben Namen und Vornamen vorhanden ist,
     * wird diese entfernt und die neue Person hinzugefügt.
     */
    public void addPerson(String name, String vorname) {
        if (removePerson(name, vorname)) {
            listPerson.add(new ListPersonAddRemoveFunction(name, vorname));
        }
    }
    /**
     * Methode zum Ändern des Vornamens und Namens einer Person.
     * Die Person wird anhand des aktuellen Vorname und Name gefunden und geändert.
     * Diese Methode gibt true zurück, wenn die Person gefunden und geändert wurde, andernfalls false.
     */
    public boolean changeNewWithOldValuePerson(String name, String vorname, String newVorname, String newName) {
        for (int i = 0; i < listPerson.size(); i++) {
            ListPersonAddRemoveFunction person = listPerson.get(i);
            if (person.getName().equals(name) && person.getVorname().equals(vorname)) {
                listPerson.set(i, new ListPersonAddRemoveFunction(newVorname, newName));
                return true;
            }
        }
        return false;
    }
}
