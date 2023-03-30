package Inheritance_Composition.inheritance;

public class TestProgram {
    public static void main(String[] args) {
        System.out.println("Dogs and cats using inheritance \n");

        IPet boris = new Dog("Boris");
        IPerson john = new Person("John", boris);

        System.out.println(john.toString() + ". " + boris.getName()
                + " is a " + boris.getClass().getSimpleName() + " and says " + boris.getSound());

        IPet chiara = new Cat("Chiara");
        IPerson jane = new Person("Jane", chiara);

        System.out.println(jane.toString() + ". " + chiara.getName() + "is a "
                + chiara.getClass().getSimpleName() + " and says " + chiara.getSound());
    }
    
}
