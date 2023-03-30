package patterns.compare;

public class PersonComparable implements Comparable<PersonComparable> {
    // only difference = implements Comparable with an object of this class
    // compare to method allows us to compare with same type objects, compare with id
    // compares composite objects of specific type
    private int id;
    private String firstName;
    private String lastName;

    public PersonComparable(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    // If the object id is the same, return 0...
    @Override
    public int compareTo(PersonComparable person) {
        if (this.id == person.getId()) {
            return 0;
        } else if (this.id < person.getId())
            return -1;
        else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return String.format("%2s", this.id) + " " + this.firstName + " " + this.lastName;
    }
}
