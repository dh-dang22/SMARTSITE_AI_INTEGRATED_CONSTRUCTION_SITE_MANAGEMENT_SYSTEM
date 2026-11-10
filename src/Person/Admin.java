package Person;

public class Admin extends Person {

    public Admin(String id, String fullName, String code, String status) {
        super(id, fullName, code, "ADMIN", status);
    }

    @Override
    protected void printRoleDetails() {
        System.out.println("Role details: System Administrator");
    }
}
