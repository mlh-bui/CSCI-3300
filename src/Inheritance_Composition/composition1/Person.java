package Inheritance_Composition.composition1;

public class Person implements IPerson {
    private String name;
    private IPet pet;


    public Person(String name, IPet pet) {
        this.name = name;
        this.pet = pet;
    }


    @Override
    public String getName() {
        return name;
    }

    public String toString() {
        return this.name +  "'s pet is " + this.pet.getName();
    }
}
