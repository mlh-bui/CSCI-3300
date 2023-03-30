package hospital.v1;

import java.time.LocalDate;

public abstract class HealthCare extends Employee {
    private Shift shift;

    public HealthCare(String ssn, String firstName, String lastName, LocalDate hireDate, double salary, boolean multilingual, Shift shift) {
        // constructor method
        super(ssn, firstName, lastName, hireDate, salary, multilingual);
        this.shift = shift;
    }

    public Shift getShift() {
        return this.shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}