package Inheritance_Composition.inheritance;

public class Cat extends Pet {

    // Cat overwrites the method getSound() declared in the superclass Pet

    public Cat (String name) {
        super(name);
    }

    public String getSound() {
        return "Meow";
    }
}
