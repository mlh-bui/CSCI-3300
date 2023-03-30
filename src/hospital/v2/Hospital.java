package hospital.v2;

import java.util.ArrayList;
import java.util.List;

public class Hospital implements IHospital {

    private String web;
    private List<IEmployee> employees;

    public Hospital(String web) {
        // constructor method
        this.web = web;
        this.employees = new ArrayList<IEmployee>();
        // constructor method
    }

    public String getWeb() {
        return this.web;
    }

    public void addEmployee(IEmployee e) {
        this.employees.add(e);
    }

    public String queryEmployees(){
        // list all employees
        String s = this.getWeb() + "\t\t\t Employee Listing \n";
        for(IEmployee e: this.employees){
            s = s + "\n" + e.toString();
        }
        s += "\n";
        return s;
    }

    public void queryEmployees(double min, double max) {
        // list employees based off salary input
        System.out.printf(this.getWeb() + "\t\t\tEmployees having a salary between %.2f and %.2f euros.\n", min, max);
        String s = "";
        for(IEmployee e: this.employees) {
           if(min < e.getSalary() && e.getSalary() < max) {
               s = s + "\n" + e.toString();
           }
        }
        s += "\n";
        System.out.println(s);

    }

    public String queryEmployees(String category) {
        // list employees based off category
        System.out.printf(this.getWeb() + "\t\t\tEmployees: %s\n", category);
        String s = "";
        for(IEmployee e: this.employees){
            if(e.getClass().getSimpleName().equals(category)) {
                s = s + "\n" + e.toString();
            }
        }
        s += "\n";
        return s;
    }
}