package org.testing.functions;

import java.util.*;

public class PlayWithArrayAndList {

    String name = null;
    int ageOfPerson = 0;
    List<PlayWithArrayAndList> peopleList = new ArrayList<>();


    public int getAgeOfPerson() {
        return ageOfPerson;
    }

    public void setAgeOfPerson(int ageOfPerson) {
        this.ageOfPerson = ageOfPerson;
    }

    public PlayWithArrayAndList() {
    }

    public PlayWithArrayAndList(String name, int ageOfPerson) {
        this.name = name;
        this.ageOfPerson = ageOfPerson;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // add a person to the list
    public void addPersonObject(PlayWithArrayAndList person) {
        peopleList.add(person);
    }

    // find Person in the list
    public boolean findPerson(String name) {
        for (PlayWithArrayAndList person : peopleList) {
            if (person.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean findTestObject(PlayWithArrayAndList o) {
        for (PlayWithArrayAndList person : peopleList) {
            if (person.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public boolean ChangeNameOfPerson(String name) {
        boolean found = false;
        for (int i = 0; i < peopleList.size(); i++) {
            if (peopleList.get(i).getName().equals(name)) {
                peopleList.get(i).setName(name);
                found = true;
            }
        }
        return found ;
    }

    public void test() {
        List<String> peopleList = new ArrayList<String>();
        Comparator sortName = Comparator.comparing(PlayWithArrayAndList::getName).thenComparing(PlayWithArrayAndList::getAgeOfPerson);
        peopleList.sort(sortName);
    }

    public boolean changeAgeOfPerson(String name, int newAge, List<PlayWithArrayAndList> peopleList) {
        if (peopleList == null) {
            return false;
        }
        if (peopleList.isEmpty()) {
            return false;
        }
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (newAge < 0) {
            return false;
        }
        //check TestObject is not null

        boolean found = false;
        for (PlayWithArrayAndList person : peopleList) {
            if (person != null && person.getName() != null && person.getName().equals(name)) {
                person.setAgeOfPerson(newAge);
                found = true;

            }
        }
        if (!found) {
             return false;
        }
        return true;
    }


    public boolean tee(String[][] dataToCheck ,String GesucherWert) {
     boolean found = false;
     int counter = 0;
        if (
                dataToCheck  == null ||
                dataToCheck.length == 0 ||
                GesucherWert == null ||
                GesucherWert.isEmpty()) {
           return  false;
        }

        for (String[] d : dataToCheck) {
            if ( d == null || d.length == 0) {
               break;
            }
            for (String value : d) {
                if( value!= null && value.equals(GesucherWert)){
                    found = true;
                    counter++;
                }
            }
        }
        return found;
    }

    public boolean containsValue(String[][] dataToCheck, String searchedValue) {

        if (dataToCheck == null ) {
            return false;
        }
        if (searchedValue == null || searchedValue.isEmpty()) {
            return false;
        }
        for (String[] d : dataToCheck) {
            if (d == null) {
                break;
            }
            for (String value : d) {
                if (value != null && value.equals(searchedValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean removeColumFromStringArray(String[][] dataToCheck) {
        boolean found = false;
        if (null == dataToCheck || dataToCheck.length !=0) {
            return found;
        }
        found = true;
        return found;
   }

    public boolean changeValueInStringArray(String[][] dataToCheck, String newValue, String oldValue) {
       boolean changeMade = false;
       if(dataToCheck == null || dataToCheck.length < 0) {
           return changeMade;
       }
       if(newValue == null || newValue.isEmpty() || oldValue == null || oldValue.isEmpty()) {
           return changeMade;
       }

       for (String[] d : dataToCheck) {
               if( d == null || d.length < 0) { return changeMade; }
                for (String value : d) {
                    if (value!= null && value.equals(oldValue)) {
                        d[0] = newValue;
                        changeMade = true;
                        break;
                    }
                }
               if( d == null || d.length < 0) { return changeMade; }
                for (String value : d) {
                    if (value != null && value.equals(oldValue)) {
                        d[d.length - 1] = newValue;
                        changeMade = true;
                        break;
                    }
                }
           }
           if(changeMade) {
               System.out.println(Arrays.deepToString(dataToCheck));
           }


       return changeMade;
   }
    public int getAge() {
        return ageOfPerson;
    }

    public void setAge(int age) {
        this.ageOfPerson = age;
    }

    public boolean Test (String[][] dataToCheck, String newValue, String oldValue) {
        // check new value is not null or empty
        if (dataToCheck == null || dataToCheck.length < 0) {
           throw new IllegalArgumentException("dataToCheck is null or empty");
        }
        if (newValue == null || newValue.isEmpty() || oldValue == null || oldValue.isEmpty()) {
            throw new IllegalArgumentException("newValue or oldValue is null or empty");
        }
        for (String[] d : dataToCheck) {
            if (d == null || d.length < 0) {
                throw new IllegalArgumentException("dataToCheck is null or empty");
            }
             // check new value is not null or empty  or the same as the old value then replace the old value with the new value
            for (String value : d) {
                if (value!= null && value.equals(oldValue)) {
                    d[0] = newValue;
                    break;
                }
            }
            if (d == null || d.length < 0) {
                throw new IllegalArgumentException("dataToCheck is null or empty");
            }

        }
        return false;
    }

    public void searchBSF(String[][] cell, int i, int j) {
        if (cell == null) {
            throw new IllegalArgumentException("cell is null");
        }
        if (i < 0 || i >= cell.length) {
            throw new IllegalArgumentException("i is out of bounds");
        }
        if (cell[i] == null || j < 0 || j >= cell[i].length) {
            throw new IllegalArgumentException("j is out of bounds");
        }
        if (cell[i][j] == null) {
            throw new IllegalArgumentException("cell[" + i + "][" + j + "] is null");
        }
        if (cell[i][j].equals(name)) {
            System.out.println(cell[i][j]);
        } else {
            searchBSF(cell, i + 1, j);
            searchBSF(cell, i, j + 1);
        }
    }

    public boolean test(String[] stringArray, String searchString) {
        if (stringArray == null || stringArray.length == 0  ) {
          return false;
        }
        if (searchString == null || searchString.isEmpty()) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < searchString.length(); i++) {
            char searchChar = searchString.charAt(i);
            if (searchChar == searchString.charAt(searchString.length() - 1)) {
                stack.push(searchChar);
                continue;
            }
            if (stack.isEmpty() || stack.peek() != searchChar) {
                return false;
            }
            stack.pop();
        }
        return stack.isEmpty();
    }


}