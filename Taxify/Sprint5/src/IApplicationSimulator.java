// Sprint 5 Project: Taxify
// Marissa Bui - CSCI 3300

public interface IApplicationSimulator {

    void show();
    void showStatistics();
    void update();
    void requestService();
    int getTotalServices();
    void requestSharedService();
    void cancelService();

} // interface IApplicationSimulator