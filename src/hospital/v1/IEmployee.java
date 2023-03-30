package hospital.v1;

import java.time.LocalDate;

public interface IEmployee {

    String getSSN();
    String getFirstName();
    String getLastName();
    LocalDate getHireDate();
    double getSalary();
    boolean getMultilingual();
    int yearsOfService();
    double salaryIncrease();
    String toString();

}