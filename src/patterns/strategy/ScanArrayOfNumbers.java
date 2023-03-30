package patterns.strategy;

public class ScanArrayOfNumbers {
    private int count;
    public ScanArrayOfNumbers(String[] numbers, INumberCheck numberCheck) {
        this.count = 0;
        // scan an array of strings, counts all times the check is true
        for(int i = 0; i < numbers.length; i++) {
            if(numberCheck.check(numbers[i])) {
                this.count++;
            }
        }
    }

    public int getCount() {
        return this.count;
    }
}
