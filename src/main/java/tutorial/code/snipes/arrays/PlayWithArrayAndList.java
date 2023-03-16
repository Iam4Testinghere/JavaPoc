package tutorial.code.snipes.arrays;

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
    // Person zur Liste hinzufügen
    public void addPersonObject(PlayWithArrayAndList person) {
        peopleList.add(person);
    }
    // Person in der Liste suchen
    public boolean findPerson(String name) {
        for (PlayWithArrayAndList person : peopleList) {
            if (person.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    // Person in der Liste suchen
    public boolean findTestObject(PlayWithArrayAndList o) {
        for (PlayWithArrayAndList person : peopleList) {
            if (person.equals(o)) {
                return true;
            }
        }
        return false;
    }
    // Name einer Person in der Liste ändern
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
    // Alter einer Person in der Liste ändern
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
    // Durchsuchen eines zweidimensionalen Arrays nach einem Wert
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
        // Überprüfe, ob das Array nicht null ist und mindestens eine Zeile enthält
        if (null == dataToCheck || dataToCheck.length !=0) {
            return found;  // Wenn das Array nicht null ist und mindestens eine Zeile enthält, wird "found" auf "true" gesetzt
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
}