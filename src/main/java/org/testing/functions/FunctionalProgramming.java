package org.testing.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionalProgramming {

    String PersonVorname;
    int ageOfPerson;
    List<FunctionalProgramming> personExists  = null;
    public FunctionalProgramming(String personVorname, int ageOfPerson) {
        PersonVorname = personVorname;
        this.ageOfPerson = ageOfPerson;
    }

    public FunctionalProgramming() {

    }

    List<FunctionalProgramming> personList = new ArrayList<>();

    public String getPersonVorname() {
        return PersonVorname;
    }

    public void setPersonVorname(String personVorname) {
        PersonVorname = personVorname;
    }

    public int getAgeOfPerson() {
        return ageOfPerson;
    }

    public void setAgeOfPerson(int ageOfPerson) {
        this.ageOfPerson = ageOfPerson;
    }


    public boolean showPersonList(String findPerson, List<FunctionalProgramming> personList) {

        if (findPerson == null || findPerson.trim().isEmpty()) {
            return false;
        }

        if (personList == null || personList.isEmpty()) {
            return false;
        }
        for (FunctionalProgramming person : personList) {
            if (person != null && person.getPersonVorname() != null && person.getPersonVorname().equals(findPerson)) {
                return true;
            }
        }
        return false;
    }

    public boolean showPersonList2(String findPerson, List<FunctionalProgramming> personList) {

        // check for null and empty findPerson
        if (findPerson == null || findPerson.trim().isEmpty()) {
            return false;
        }
        //check for null and empty personList
        if (personList == null || personList.isEmpty()) {
            return false;
        }
        // loop through personList
        for (FunctionalProgramming person : personList) {
            if (person != null && person.getPersonVorname() != null && person.getPersonVorname().equals(findPerson)) {
                personExists.add(person);
                return true;
            }
        }
        return false;
    }
     void tet( List<FunctionalProgramming> personList){
         personList.stream().filter(p -> p.getPersonVorname().equals(PersonVorname) && p.getAgeOfPerson() == ageOfPerson);
     }

     public void Test4(){

         Consumer<String> c  = c1 -> {
             String sw = new String("hallo");
         };
         Runnable r1 = () -> {
             new String();
             new Integer(1);
         };
         Supplier<Integer> sup =  () ->  1;
         Supplier<Person> p =  () ->  {
             return new Person("om",1);
         };
         Function<String,Boolean> f = f1 -> f1.isEmpty();


         Optional<String> optional = Optional.ofNullable(null);
         optional.ifPresent(c11 -> System.out.println(c11));


     }
     void test23(String a){

     }
     public  Integer test34(){
           return new Integer(1);
     }


}
