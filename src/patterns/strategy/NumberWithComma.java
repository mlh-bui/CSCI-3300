package patterns.strategy;

public class NumberWithComma implements INumberCheck {

    public boolean check(String s) {
        return s.contains(",");
    }

}