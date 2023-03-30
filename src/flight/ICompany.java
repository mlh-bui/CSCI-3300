package flight;

public interface ICompany {
    String getWeb();
    void addAircraft(IAircraft ar);
    void addPilot(IPilot p);
    void operateFlight(IAircraft ar, IPilot p, int m);
    void showAircraftStats();
    void showPilotStats();
}
