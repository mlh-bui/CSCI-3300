package patterns.builder;

import java.time.LocalDate;

/*
 * The Builder pattern avoids overloading constructor methods, it also allows object immutability after instantiation
 */

public class User {
    private String email;
    private String name;
    private int birthYear;
    private char gender;
    private String country;

    private User(Builder user) {
        this.email = user.email;
        this.name = user.name;
        this.birthYear = user.birthYear;
        this.gender = user.gender;
        this.country = user.country;
    }

    public String toString() {
        return this.email + "\t" + this.name + "\t" + this.birthYear + "\t" + ((this.gender != ' ') ? ((this.gender == 'M') ? "Male  " : "Female") : "") + "\t" + this.country;
    }

    /*
     *  User class declares the static nested class Builder for object instantiation. Inner classes can be private, public or protected
     */

    public static class Builder {
        private String email;
        private String name;
        private int birthYear;
        private char gender;
        private String country;

        // constructor method for mandatory arguments

        public Builder(String email, String name, int birthYear) {
            this.email = email;
            this.name = name;
            this.birthYear = birthYear;
            this.gender = ' ';
            this.country = "";
        }

        // set methods for optional arguments

        public Builder setGender(char gender) {
            if (gender != 'M' && gender != 'F')
                throw new IllegalArgumentException("Invalid gender for " + this.name + "!");

            this.gender = gender;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        // build method includes data validation

        public User build() {
            User user = new User(this);

            if (LocalDate.now().getYear() - user.birthYear < 18)
                throw new IllegalArgumentException("Invalid legal age for " + this.name + "!");

            return new User(this);
        }
    }
}