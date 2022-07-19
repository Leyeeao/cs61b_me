import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class User implements Comparable<User> {

    private static int nextId = 1;

    private int id;
    private int age;
    private String name;
    private String email;

    public User(String name, String email) {
        this(nextId, name, email);
        nextId += 1;
    }

    /** Force assign an id to a created user **/
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        setAge();
    }

    /** For this assignment, age is just an automatically assigned field. */
    void setAge() {
        age = (id % 13) + 20;
    }

    int getAge() {
        return age;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + "}";
    }

    public int compareTo(User otherUser) {
        if(this.id != otherUser.id) {
            return this.id - otherUser.id;
        } else{
            return getNameComparator().compare(this, otherUser);
        }
    }

    private static class NameComparator implements Comparator<User> {
        public int compare(User a, User b){
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<User> getNameComparator() {
        return new NameComparator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User other = (User) o;
        if (id != other.id) {
            return false;
        } else if (!Objects.equals(name, other.name)) {
            return false;
        } else {
            return Objects.equals(email, other.email);
        }
    }



    public static void main(String[] args) {
        User[] users = {
            new User(2, "Jedi", ""),
            new User(4, "Zoe", ""),
            new User(5, "Allyson", ""),
            new User(1, "Sohum", ""),
            new User(1, "Nandini", "")
        };
        Arrays.sort(users);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
