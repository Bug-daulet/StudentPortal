package rest.Model;

public class Admin extends User {
    public Admin(int id, String email, String password, String firstName, String lastName, String role) {
        super(id, email, password, firstName, lastName, role);
    }
}
