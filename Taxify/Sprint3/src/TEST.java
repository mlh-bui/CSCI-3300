import java.util.ArrayList;
import java.util.List;

public class TEST {
    private List<Integer> users;

    public TEST(List<Integer> users) {
        this.users = users;
    }

    private void addUsers() {
        users.add(4444);
        users.add(12345);
        users.add(5678);
        users.add(567890);
        users.add(9876543);
        users.add(123456);

    }

    private int indexOfUserId(int id) {
        // finds the index of a user with the input id in the list, otherwise it returns -1
        for(int i = 0; i < this.users.size(); i++) {
            if(this.users.get(i) == id) {   // if id = the id of the user at position i
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<Integer> s1 = new ArrayList<>();
        TEST t1 = new TEST(s1);
        t1.addUsers();
        System.out.println(t1.indexOfUserId(5678));
    }


}
