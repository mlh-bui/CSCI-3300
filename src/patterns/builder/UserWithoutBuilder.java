package patterns.builder;

public class UserWithoutBuilder {
    private String email;
    private String name;
    private int birthYear;
    private char gender;
    private String country;

    // the constructor method is overloaded

    public UserWithoutBuilder(String email, String name, int birthYear) {
        this.email = email;
        this.name = name;
        this.birthYear = birthYear;
        this.gender = ' ';
        this.country = "";
    }

    public UserWithoutBuilder(String email, String name, int birthYear, char gender) {
        this.email = email;
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.country = "";
    }

    public UserWithoutBuilder(String email, String name, int birthYear, char gender, String country) {
        this.email = email;
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.country = country;
    }

    public String toString() {
        return this.email + "\t" + this.name + "\t" + this.birthYear + "\t" + ((this.gender != ' ') ? ((this.gender == 'M') ? "Male  " : "Female") : "") + "\t" + this.country;
    }
}
