package hospital.v1;

import java.time.LocalDate;

public class Staff extends Employee {

    public Staff(String ssn, String firstName, String lastName, LocalDate hireDate, double salary, boolean multilingual) {
        // constructor method
        super(ssn, firstName, lastName, hireDate, salary, multilingual);
    }

    public double salaryIncrease() {
        // calculates the salary increase for Staff
        double salaryIncrease;
        int triennial = yearsOfService() / 3;
        if (getMultilingual()) {
            salaryIncrease = 0.005 + 0.03 + 0.003 * triennial;
        } else {
            salaryIncrease = 0.03 + 0.003 * triennial;
        }
        return salaryIncrease;
    }

}