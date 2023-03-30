package Inheritance_Composition.inheritance;

public abstract class Pet implements IPet {
    private String name;

    public Pet(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.getName();
    }

    public abstract String getSound();
}
