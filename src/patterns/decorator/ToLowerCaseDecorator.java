package patterns.decorator;

public class ToLowerCaseDecorator extends TextFileDecorator {

    public ToLowerCaseDecorator(IFile data) {
        super(data);
    }

    @Override
    public void write(String [] data) {
        for (int i=0; i<data.length; i++)
            data[i] = data[i].toLowerCase();

        super.write(data);
    }
}