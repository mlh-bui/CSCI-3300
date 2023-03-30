package patterns.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestProgram {

    public static void main(String[] args) {
        int index;

        // Java binary search using PersonComparable objects

        List<PersonComparable> people1 = new ArrayList<PersonComparable>();

        people1.add(new PersonComparable(1, "John", "Williams"));
        people1.add(new PersonComparable(3, "Jane", "Roberts"));
        people1.add(new PersonComparable(5, "Peter", "Adams"));
        people1.add(new PersonComparable(6, "Julia", "Andrews"));
        people1.add(new PersonComparable(7, "Mary", "Johnson"));
        people1.add(new PersonComparable(9, "Rose", "Jameson"));
        people1.add(new PersonComparable(10, "Laura", "Roberts"));
        people1.add(new PersonComparable(11, "Daniel", "Davis"));
        people1.add(new PersonComparable(12, "Jeff", "Andrews"));
        people1.add(new PersonComparable(14, "Marie", "Cameron"));
        people1.add(new PersonComparable(15, "Paul", "Anderson"));
        people1.add(new PersonComparable(18, "Michael", "Scott"));
        people1.add(new PersonComparable(20, "Helen", "Smith"));
        people1.add(new PersonComparable(22, "Patricia", "Wright"));
        people1.add(new PersonComparable(25, "Noah", "Harris"));

        // Does a binary search with this list
        // Output is index, find if object we've searched if it is in the list
        index = Collections.binarySearch(people1, new PersonComparable(15, "Paul", "Anderson"));

        if (index < 0)
            System.out.println("Paul Anderson not found!");
        else
            // negative means the value is not found in the list
            System.out.println("Paul Anderson found at index " + index);

        // Java binary search using Person objects and the comparator PersonComparator

        List<Person> people2 = new ArrayList<Person>(); // data type is Person not PersonComparable

        people2.add(new Person(1, "John", "Williams"));
        people2.add(new Person(3, "Jane", "Roberts"));
        people2.add(new Person(5, "Peter", "Adams"));
        people2.add(new Person(6, "Julia", "Andrews"));
        people2.add(new Person(7, "Mary", "Johnson"));
        people2.add(new Person(9, "Rose", "Jameson"));
        people2.add(new Person(10, "Laura", "Roberts"));
        people2.add(new Person(11, "Daniel", "Davis"));
        people2.add(new Person(12, "Jeff", "Andrews"));
        people2.add(new Person(14, "Marie", "Cameron"));
        people2.add(new Person(15, "Paul", "Anderson"));
        people2.add(new Person(18, "Michael", "Scott"));
        people2.add(new Person(20, "Helen", "Smith"));
        people2.add(new Person(22, "Patricia", "Wright"));
        people2.add(new Person(25, "Noah", "Harris"));

        // pass an object which contains the new PersonComparator,
        // since it does not implement the compare in the class Person
        index = Collections.binarySearch(people2, new Person(3, "Jane", "Roberts"), new PersonComparator());

        if (index < 0)
            System.out.println("Jane Roberts not found!");
        else
            System.out.println("Jane Roberts found at index " + index);

        // want to sort people by ids in descending order
        Person[] people3 = new Person[people2.size()];

        // copy items from people2 into 3
        for(int i = 0; i < people2.size(); i++) {
            people3[i] = people2.get(i);
        }

        Arrays.sort(people3, new PersonComparatorSortDescending());
        // since we are sorting a list of people need the new PersonComparatorSort

        for(Person p : people3) {
            System.out.print("\n" + p.toString());
        }

        Arrays.sort(people3, new PersonComparatorSortAscending());

        System.out.println();

        for(Person p : people3) {
            System.out.print("\n" + p.toString());
        }

        // Like strategy where we can write a method to manipulate objects differently but different
    }

}