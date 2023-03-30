package hospital.v2;

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

    // Standard bonus for all Healthcare staff besides primary care
    public double bonus(Shift shift) {
        double bonus;
        if(shift == Shift.DAY) {
            bonus = 0.0035;
        } else if (shift == Shift.ROTATED) {
            bonus = 0.015;
        } else {
            bonus = 0.007;
        }
        bonus = bonus * getSalary();
        return bonus;
    }

    public String toString() {
        // returns standard language if monolingual
        String language = "English";
        if(getMultilingual()) {
            language = "Multilingual";
        }

        String s = String.format("%-5s ", getSSN())
                + String.format("%10s, ", getLastName())
                + String.format("%-10s", getFirstName())
                + String.format("%5s years", yearsOfService())
                + String.format("%10.1f eur.", getSalary())
                + String.format("%15s\t", language)
                + String.format("%-15s", getClass().getSimpleName())
                + String.format("Salary Increase %.2f eur.", (salaryIncrease() * getSalary()))
                + String.format("\t\tBonus: %.1f", (bonus(this.shift)));

        return s;
    }
}