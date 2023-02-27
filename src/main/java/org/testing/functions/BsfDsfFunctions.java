package org.testing.functions;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class BsfDsfFunctions {

    public int getAge() {
        return age;
    }

    int age;
    List<BsfDsfFunctions> list;
    String name;

    public BsfDsfFunctions() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BsfDsfFunctions> getList() {
        return list;
    }

    public void setList(List<BsfDsfFunctions> list) {
        this.list = list;
    }

    public BsfDsfFunctions(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // create function searchPersonDFS using depth first search§
  public  BsfDsfFunctions searchPersonDFS(String name, int age, List<BsfDsfFunctions> list) {
        //check null and empty
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        if (age < 0) {
            throw new IllegalArgumentException("");
        }
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        for (BsfDsfFunctions person : list) {
            if (person == null) {
                throw new IllegalArgumentException(" Das ist mein Test");
            }
            if (person.getName().equals(name) && person.getAge() == age) {
                return person;
            }
            BsfDsfFunctions result = searchPersonDFS(name, age, person.getList());
            if (result != null) {
                return result;
            }
        }
        return null ;
    }


    // create function searchPersonBSF using breadth first search
    public BsfDsfFunctions searchPersonBSF(String name, int age, List<BsfDsfFunctions> list) throws IllegalArgumentException {
        // check null and empty
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        if (age < 0) {
            throw new IllegalArgumentException("");
        }
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        Queue<BsfDsfFunctions> queue = new LinkedList<>();
        queue.addAll(list);
        while (!queue.isEmpty()) {
            BsfDsfFunctions person = queue.remove();
            if (person == null) {
               throw new IllegalArgumentException(" ");
            }
            if ( person.getName() == null ) {
                throw new IllegalArgumentException(" prob");
            }
            if ( person.getAge() < 0) {
                throw new IllegalArgumentException(" prob");
            }
            if (person.getName().equals(name) && person.getAge() == age) {
                return person;
            }
            // check getList ist nicht null
            if (person.getList() != null && person.getList().isEmpty()) {
                queue.addAll(person.getList());
            }
        }
        return null;
    }

    public BsfDsfFunctions searchPersonBSF_gpt(String name, int age, List<BsfDsfFunctions> list) throws IllegalArgumentException {
        // check null and empty
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht null oder leer sein.");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Alter darf nicht negativ sein.");
        }
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Liste darf nicht null oder leer sein.");
        }
        Queue<BsfDsfFunctions> queue = new LinkedList<>();
        queue.addAll(list);
        while (!queue.isEmpty()) {
            BsfDsfFunctions person = queue.remove();
            if (person == null) {
                throw new IllegalArgumentException("Test-Objekt in der Liste darf nicht null sein.");
            }
            if (person.getName() == null) {
                throw new IllegalArgumentException("Name des Test-Objekts darf nicht null sein.");
            }
            if (person.getAge() < 0) {
                throw new IllegalArgumentException("Alter des Test-Objekts darf nicht negativ sein.");
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

    enum TestType {
        MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG, SAMSTAG;
    }

    public void test() {
        Stream<String> s = Stream.of("MONTAG");
        s.forEach(f -> {
            boolean found = TestType.valueOf(f).equals(TestType.MONTAG);
            if (found) {
                throw new IllegalArgumentException(" Keine");
            }
        });
    }
    public void testwas(String name, int age, List<String> list) {
         // check null and empty
        if (name == null || name.isEmpty()||age < 0||list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht null oder leer sein.");
        }
         for(String s : list) {
             if (s == null || s.isEmpty()) {
                 throw new IllegalArgumentException("Name darf nicht null oder leer sein.");
             }
         }
        list.stream()
               .filter(f -> f != null ||!f.isEmpty()&&f.equals(name))
               .findAny()
               .orElseThrow(() -> new IllegalArgumentException(" Schon vorhanden")) ;
    }

}
