package patterns.compare;

import java.util.Comparator;

public class PersonComparatorSortDescending implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return (int) (p2.getId() - p1.getId());
    }
}
