package Inheritance_Composition.composition3;

public class Cat extends Pet {
    IPetSound meow;

    // Cat overwrites the method getSound() declared in the superclass Pet

    public Cat (String name) {
        super(name);
    }

    public String getSound() {
        return this.meow.getSound();
    }
}