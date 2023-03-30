package patterns.composite;

public class TextFile implements IFile {
    private String name;
    private int bytes;

    public TextFile(String name, int bytes) {
        // constructor method
        this.name = name;
        this.bytes = bytes;
    }

    @Override
    public String getName() {
        // returns name
        return this.name;
    }

    @Override
    public int getBytes() {
        // returns the size in bytes
        return this.bytes;
    }
}