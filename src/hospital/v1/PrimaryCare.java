package hospital.v1;

import java.time.LocalDate;

public class PrimaryCare extends HealthCare {

    public PrimaryCare(String ssn, String firstName, String lastName, LocalDate hireDate, double salary, boolean multilingual, Shift shift) {
        super(ssn, firstName, lastName, hireDate, salary, multilingual, shift);
        // constructor method
    }

    public double salaryIncrease() {
        // calculates the salary increase for Primary Care Doctors
        int quinquennial = yearsOfService() / 5;
        return 0.035 + 0.005 * quinquennial;
    }

}