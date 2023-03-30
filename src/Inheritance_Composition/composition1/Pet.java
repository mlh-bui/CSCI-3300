// class Pet

package Inheritance_Composition.composition1;

public class Pet implements IPet {
    private String name;
    private IPetSound sound;

    public Pet(String name, IPetSound sound) {
        this.name = name;
        this.sound = sound;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSound() {
        return this.sound.getSound();
    }
}