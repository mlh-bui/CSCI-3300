package patterns.builder;

public class TestProgram {

    public static void main(String[] args) {

        try {

            UserWithoutBuilder james = new UserWithoutBuilder("james.Reed@hotmail.com", "James", 2000, 'M', "US");
            UserWithoutBuilder rose = new UserWithoutBuilder("rose.johnson@gmail.com", "Rose", 1995, 'F');

            System.out.println(james.toString());
            System.out.println(rose.toString());

            User jane = new User.Builder("jane.smith@gmail.com", "Jane", 2000).setGender('F').build();
            User peter = new User.Builder("peter.rice@gmail.com", "Peter", 1998).setGender('M').setCountry("US").build();

            System.out.println(jane.toString());
            System.out.println(peter.toString());

            User john = new User.Builder("john.powel@hotmail.com", "John", 2010).setGender('X').setCountry("US").build();

            System.out.println(john.toString());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}