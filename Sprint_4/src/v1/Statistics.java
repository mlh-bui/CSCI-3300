// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300
package v1;

public class Statistics implements IStatistics {
    /**
     * Rating of stars
     */
    private double stars;

    /**
     * Distance user travelled
     */
    private int distance;

    /**
     * Bill/cost of service
     */
    private int billing;

    /**
     * Number of services
     */
    private int services;

    /**
     * User reviews of drivers
     */
    private int reviews;

    /**
     * Basic constructor
     */
    public Statistics() {
        this.services = 0;
        this.reviews = 0;
        this.stars = 0;
        this.distance = 0;
        this.billing = 0;
    }

    /*
    Accessors & Mutators
    */
    public int getServices() {
        return this.services;
    }

    public int getReviews() {
        return this.reviews;
    }

    public double getStars() {
        double stars = this.stars / (double) this.reviews;

        return Math.round(stars * 100.0) / 100.0;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getBilling() {
        return this.billing;
    }

    public void updateServices() {
        this.services++;
    }

    public void updateReviews() {
        this.reviews++;
    }

    public void updateStars(int stars) {
        this.stars += stars;
    }

    public void updateDistance(int distance) {
        this.distance += distance;
    }

    public void updateBilling(int billing) {
        this.billing += billing;
    }
} // class statistics