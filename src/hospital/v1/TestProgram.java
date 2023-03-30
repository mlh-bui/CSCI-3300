// 1/31/23 Exercise
// Marissa Bui - COMP 330/CSCI 3300
package hospital.v1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestProgram {

    public static void main(String[] args) {

        IHospital mc = new Hospital("www.mc-hospitals.com");

        mc.addEmployee(new Staff("01", "James", "Williams", LocalDate.parse("09-14-2017", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 32750.0, false));
        mc.addEmployee(new Staff("02", "Amanda", "Jones", LocalDate.parse("05-15-2012", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 38500.0, true));
        mc.addEmployee(new Staff("03", "David", "Brown", LocalDate.parse("07-01-2018", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 32500.0, false));
        mc.addEmployee(new Staff("04", "Robert", "Smith", LocalDate.parse("02-10-2015", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 35000.0, true));
        mc.addEmployee(new Staff("05", "Jessica", "Miller", LocalDate.parse("11-11-2016", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 36000.0, false));

        mc.addEmployee(new Nurse("06", "Jennifer", "Johnson", LocalDate.parse("01-02-2021", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 39000.0, true, Shift.DAY));
        mc.addEmployee(new Nurse("07", "Jane", "Campbell", LocalDate.parse("12-12-2017", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 36500.0, false, Shift.DAY));
        mc.addEmployee(new Nurse("08", "Alice", "Richards", LocalDate.parse("10-12-2015", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 38500.0, false, Shift.NIGHT));
        mc.addEmployee(new Nurse("09", "Laura", "Powell", LocalDate.parse("02-14-2021", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 40000.0, false, Shift.DAY));
        mc.addEmployee(new Nurse("10", "Albert", "Rice", LocalDate.parse("05-15-2018", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 42000.0, true, Shift.DAY_WITH_BEEPER_CALLS));

        mc.addEmployee(new Surgeon("11", "David", "Martin", LocalDate.parse("04-12-2015", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 60000.0, true, Shift.DAY));
        mc.addEmployee(new Surgeon("12", "Matthew", "Davis", LocalDate.parse("06-22-2014", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 62500.0, false, Shift.NIGHT));
        mc.addEmployee(new Surgeon("13", "Paul", "Adams", LocalDate.parse("11-02-2010", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 65500.0, true, Shift.ROTATED));

        mc.addEmployee(new Specialist("14", "Barbara", "Moore", LocalDate.parse("03-05-2010", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 67250.0, false, Shift.DAY));
        mc.addEmployee(new Specialist("15", "Linda", "Tundo", LocalDate.parse("01-28-2012", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 65500.0, true, Shift.NIGHT));
        mc.addEmployee(new Specialist("16", "Thomas", "Mitchel", LocalDate.parse("12-12-2020", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 55000.0, true, Shift.DAY));

        mc.addEmployee(new PrimaryCare("17", "Mary", "Thompson", LocalDate.parse("01-14-2016", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 59000.0, true, Shift.DAY));
        mc.addEmployee(new PrimaryCare("18", "Elizabeth", "Harris", LocalDate.parse("03-15-2019", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 57000.0, true, Shift.ROTATED));
        mc.addEmployee(new PrimaryCare("19", "David", "White", LocalDate.parse("12-12-2021", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 55000.0, false, Shift.DAY));

        System.out.println(mc.queryEmployees());
        mc.queryEmployees(35000, 65000);
        System.out.println(mc.queryEmployees("Staff"));
        System.out.println(mc.queryEmployees("Nurse"));
        System.out.println(mc.queryEmployees("Specialist"));
        System.out.println(mc.queryEmployees("PrimaryCare"));

    }
}