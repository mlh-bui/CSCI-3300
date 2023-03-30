package flight;

import java.util.ArrayList;
import java.util.List;

public class Company implements ICompany {
    private String web;
    private List<IPilot> pilots;
    private List<IAircraft> aircrafts;

    public Company(String web) {
        // constructor method
        this.web = web;
        this.pilots = new ArrayList<IPilot>();
        this.aircrafts = new ArrayList<IAircraft>();
    }
    public String getWeb() {
        return this.web;
    }
    public void addAircraft(IAircraft a) {
        this.aircrafts.add(a);
    }

    public void addPilot(IPilot p) {
        this.pilots.add(p);
    }

    public void operateFlight(IAircraft a, IPilot p, int miles) {
        a.addMiles(miles);
        p.addMiles(miles);
        p.flyAircraft(a);
        System.out.printf("Pilot %s flies aircraft %s", a.getId(), p.getId());
        System.out.printf("\n%s %s flies %d miles", a.getClass().getSimpleName(), a.getId(), a.getMiles());
    }

    public void showAircraftStats() {
        System.out.printf("\n\n%s \t Aircraft Statistics\n", this.getWeb());
        for(IAircraft a: this.aircrafts) {
                System.out.format("%s %d miles\n", a.getId(), a.getMiles());
        }
    }

    public void showPilotStats() {
        System.out.printf("\n\n%s \t Crew Statistics\n", this.getWeb());
        for(IPilot p: this.pilots) {
            System.out.format("%s %d miles\n", p.getId(), p.getMiles());
        }
    }
}