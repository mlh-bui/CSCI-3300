package Inheritance_Composition.inheritance;

public class Dog extends Pet {

    // Cat overwrites the method getSound() declared in the superclass Pet

    public Dog (String name) {
        super(name);
    }

    public String getSound() {
        return "Woof";
    }
}