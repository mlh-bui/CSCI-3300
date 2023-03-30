package patterns.decorator;

public class TextFileDecorator implements IFile {
    private IFile data;

    public TextFileDecorator(IFile data) {
        this.data = data;
    }

    @Override
    public void write(String [] data) {
        this.data.write(data);
    }

    @Override
    public void read() {
       // this.data.read(data); DOES NOT WORK??? Cancel comment later
    }
}