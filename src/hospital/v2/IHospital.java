package hospital.v2;

public interface IHospital {

    String getWeb();
    void addEmployee(IEmployee e);
    String queryEmployees();
    void queryEmployees(double min, double max);
    String queryEmployees(String category);

}