// 2/2/23 Exercise
// Marissa Bui - COMP 330/CSCI 3300

package Inheritance_Composition.composition1;

public class TestProgram {

    public static void main(String[] args) {
        System.out.println("Dogs and cats using composition \n");

        IPet boris = new Pet("Boris", new DogSound());
        IPerson john = new Person("John", boris);

        System.out.println(john.toString() + ". " + boris.getName()
                + " is a " + boris.getClass().getSimpleName() + " and says " + boris.getSound());

        IPet chiara = new Pet("Chiara", new CatSound());
        IPerson jane = new Person("Jane", chiara);

        System.out.println(jane.toString() + ". " + chiara.getName() + "is a "
                + chiara.getClass().getSimpleName() + " and says " + chiara.getSound());
    }
}