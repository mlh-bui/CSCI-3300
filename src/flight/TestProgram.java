package flight;

public class TestProgram {
    public static void main(String[] args) {
        Company test1 = new Company("www.air-charter.com");
        IAircraft a1 = new Airplane("A01",0);
        IAircraft a2 = new Airplane("A02",0);
        IAircraft a3 = new Airplane("A03",0);

        IPilot p1 = new Pilot("P01", 0);
        IPilot p2 = new Pilot("P02", 0);

        test1.operateFlight(a1, p1,400);

        test1.addAircraft(a1);
        test1.addAircraft(a2);
        test1.addAircraft(a3);
        test1.addPilot(p1);
        test1.addPilot(p2);

        test1.showAircraftStats();
        test1.showPilotStats();
    }

}
