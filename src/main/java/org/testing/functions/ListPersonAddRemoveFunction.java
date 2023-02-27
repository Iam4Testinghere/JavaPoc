package org.testing.functions;

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

    /* remove from list using name */

    public void remove(String vorname, String name) {
        Iterator<ListPersonAddRemoveFunction> it = listPerson.iterator();
        while (it.hasNext()) {
            ListPersonAddRemoveFunction p = it.next();
            if (p.getVorname().equals(vorname) && p.getName().equals(name)) {
                it.remove();
            }
        }
    }

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

    /* list all entry from list */
    public List<ListPersonAddRemoveFunction> getListPerson() {
        return listPerson;
    }

    /* add new Person */
    public void addPerson(String name, String vorname) {
        /* check name and Vorname exists */
        if (removePerson(name, vorname)) {
            listPerson.add(new ListPersonAddRemoveFunction(name, vorname));
        }
    }

    /* alter name or alter vorname */
    public boolean alterPerson(String name, String vorname, String newVorname, String newName) {

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
