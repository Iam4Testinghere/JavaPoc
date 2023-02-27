package org.testing.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnyMatchWithClassPerson {


    class Person {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getBod() {
            return bod;
        }

        public void setBod(int bod) {
            this.bod = bod;
        }

        private  String name;
     private  int age;
     private  int bod ;
     List<Person> list = new ArrayList<>();
        public Person(String name, int age, int bod) {
            this.name = name;
            this.age = age;
            this.bod = bod;
        }
    }
     List<Person> personListSort ( List<Person> list){
        if(list.stream().anyMatch(p -> p == null|| p.getName() == null || p.getName().trim().isEmpty() ||p.getAge() < 0||p.getBod() < 0)){
            throw new IllegalArgumentException(" Arume");
        }
        // create a list and use merge sort
         return list.stream().sorted().collect(Collectors.toList());
     }
     List<Person> personListSort2 ( List<Person> list){

         if(list.stream().anyMatch(p -> p == null|| p.getName() == null || p.getName().trim().isEmpty() ||p.getAge() < 0||p.getBod() < 0)){
             throw new IllegalArgumentException(" Arume");
         }

        return null;
     }
}
