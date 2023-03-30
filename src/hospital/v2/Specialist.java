package hospital.v2;

import java.time.LocalDate;

public class Specialist extends HealthCare {

    public Specialist(String ssn, String firstName, String lastName, LocalDate hireDate, double salary, boolean multilingual, Shift shift) {
        // constructor method
        super(ssn, firstName, lastName, hireDate, salary, multilingual, shift);
    }

    public double salaryIncrease() {
        // calculates the salary increase for Specialist Doctors
        int quinquennial = yearsOfService() / 5;
        return 0.045 + .01 * quinquennial;

    }

}