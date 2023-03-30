package hospital.v2;

import java.time.LocalDate;

public class Surgeon extends HealthCare {

    public Surgeon(String ssn, String firstName, String lastName, LocalDate hireDate, double salary, boolean multilingual, Shift shift) {
        super(ssn, firstName, lastName, hireDate, salary, multilingual, shift);
        // constructor method
    }

    public double salaryIncrease() {
        // calculates the salary increase for Surgeons
        int quinquennial = yearsOfService() / 5;
        return 0.05 + 1.0 * quinquennial;
    }

}