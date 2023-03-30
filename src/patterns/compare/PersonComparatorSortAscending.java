package patterns.compare;

import java.util.Comparator;

public class PersonComparatorSortAscending implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return (int) (p1.getId() - p2.getId());
    }
}
