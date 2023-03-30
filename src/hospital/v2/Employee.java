package hospital.v2;

import java.time.LocalDate;
import java.time.Period;

public abstract class Employee implements IEmployee {
    private String ssn;
    private String firstName;
    private String lastName;
    private LocalDate hireDate;
    private double salary;
    private boolean multilingual;

    public Employee(String ssn, String firstName, String lastName, LocalDate hireDate, double salary, boolean multilingual) {
        // constructor method
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.salary = salary;
        this.multilingual = multilingual;
    }

    public String getSSN() {
        // returns the SSN of the employee
        return this.ssn;
    }

    public String getFirstName() {
        // returns the first name of the employee
        return this.firstName;
    }

    public String getLastName() {
        // returns the last name of the employee
        return this.lastName;
    }

    public LocalDate getHireDate() {
        // returns the hire date of the employee
        return this.hireDate;
    }

    public double getSalary() {
        // returns the salary of the employee
        return this.salary;
    }

    public boolean getMultilingual() {
        // returns true if the employee is multilingual and false otherwise
        return this.multilingual;
    }

    public int yearsOfService() {
        // calculates the years of service using the hire date and the current date
        return Period.between(getHireDate(), LocalDate.now()).getYears();
    }

    public abstract double salaryIncrease();

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
                + String.format("Salary Increase %.2f eur.", (salaryIncrease() * getSalary()));

        return s;
    }
}