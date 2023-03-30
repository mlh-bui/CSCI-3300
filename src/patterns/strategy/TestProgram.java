package patterns.strategy;

public class TestProgram {

    public static void main(String[] args) {

        String [] numbers = {"123.45", "123,45", "99.0", "425", "67,3", "78.45", "12.55", "97,79"};

        ScanArrayOfNumbers scanner;

        // Calls a function called scanner with the same array but different method (or object) to check
        // uses the same interface INumberCheck with different checks (either comma or period)
        // implement different classes which implement the function
        // can pass different objects at runtime
        // creates flexibility

        scanner = new ScanArrayOfNumbers(numbers, new NumberWithComma());

        System.out.println("The array has " + scanner.getCount() + " numbers using European decimal notation");

        scanner = new ScanArrayOfNumbers(numbers, new NumberWithDecimalPoint());

        System.out.println("The array has " + scanner.getCount() + " numbers using American decimal notation");


    }
}