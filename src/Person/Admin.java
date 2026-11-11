package Person;

public class Admin extends Person {

    private String adminDetail;
    
    public Admin(String id, String fullName, String code, String status, String adminDetail) {
        super(id, fullName, code, "ADMIN", status);
        this.adminDetail = adminDetail;
    }
    public String getAdminDetail() {
        return adminDetail;
    }

    public void setAdminDetail(String adminDetail) {
        this.adminDetail = adminDetail;
    }

   
    @Override
    protected void printRoleDetails() {
        System.out.println("Role details: System Administrator");
    }
}