package patterns.strategy;


public class NumberWithDecimalPoint implements INumberCheck {

    public boolean check(String s) {
        return s.contains(".");
    }

}