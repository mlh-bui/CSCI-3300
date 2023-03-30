package patterns.compare;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    // Same way to do the same thing as PersonComparable
    // Class implements Comparator<Person>  <-- applies to person class
    // Either Person + Person Comparator with PersonComparator implementing Person
    // OR
    // PersonComparable implements Comparable <PersonComparable>    <-- same thing dif methods
    // Different data type, does the same with different approaches
    @Override
    public int compare(Person p1, Person p2) {
        return (int) (p1.getId() - p2.getId());
    }

}