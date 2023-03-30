package patterns.decorator;

public class ToUpperCaseDecorator extends TextFileDecorator {

    public ToUpperCaseDecorator(IFile data) {
        super(data);
    }

    @Override
    public void write(String [] data) {
        for (int i=0; i<data.length; i++)
            data[i] = data[i].toUpperCase();

        super.write(data);
    }
}