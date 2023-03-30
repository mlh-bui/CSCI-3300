package patterns.decorator;

public class TestProgram {

    public static void main(String[] args) {
        String [] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        TextFile file = new TextFile("data.txt");

        file.write(days);

        System.out.println("Reading data.txt \n");

        file.read();

        // Decorator adds functionality to an object at runtime
        // Decorators are an alternative to subclasses
        // why? if we have an object but not the code, can add more functionality
        TextFileDecorator lowerCase = new ToLowerCaseDecorator( new TextFile("lower.txt") );

        lowerCase.write(days);

        System.out.println("\nReading lower.txt \n");

        lowerCase.read();

        TextFileDecorator upperCase = new ToUpperCaseDecorator( new TextFile("upper.txt") );

        upperCase.write(days);

        System.out.println("\nReading upper.txt \n");

        upperCase.read();
    }

}