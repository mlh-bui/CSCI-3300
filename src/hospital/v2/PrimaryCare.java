package hospital.v2;

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

    public double bonus(Shift shift) {
        double bonus;
        if(shift == Shift.DAY) {
            bonus = 0.0025;
        } else if (shift == Shift.NIGHT) {
            bonus = 0.005;
        } else if(shift == Shift.ROTATED){
            bonus = 0.01;
        } else {
            bonus = 0.0075;
        }
        bonus = bonus * getSalary();
        return bonus;
    }
}