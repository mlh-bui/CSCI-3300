package hospital.v2;

import java.time.LocalDate;

public class Nurse extends HealthCare {

    public Nurse(String ssn, String firstName, String lastName, LocalDate hireDate, double salary, boolean multilingual, Shift shift) {
        super(ssn, firstName, lastName, hireDate, salary, multilingual, shift);
        // constructor method
    }

    public double salaryIncrease() {
        // calculates the salary increase for Nurses
        int triennial = yearsOfService() / 3;
        return 0.04 + 0.003 * triennial;
    }

}