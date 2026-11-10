package Person;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Minh_Khang
 */
public class Visitor extends Person {

    private String visitPurpose;
    private String hostName;
 //   private int accessLevel;

    public Visitor() {
        super();
    }

    public Visitor(String id, String fullName, String code, String role, String status,
            String visitPurpose, String hostName) {
        super(id, fullName, code, role, status);
        this.visitPurpose = visitPurpose;
        this.hostName = hostName;
    }

    public Visitor(String id, String fullName, String code, String status, String visitPurpose, String hostName ) {
        this(id, fullName, code, "VISITOR", status, visitPurpose, hostName);
    }

    public Visitor(String visitPurpose, String hostName) {
        this.visitPurpose = visitPurpose;
        this.hostName = hostName;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public String toFileLine() {
        return super.toFileLine() + "|" + visitPurpose + "|" + hostName;
    }

    @Override
    public String toString() {
        return super.toString() + " - Purpose: " + visitPurpose + " - Host: " + hostName;
    }
    
    @Override
        protected void printRoleDetails() {
        System.out.println("    Visit-Purpose: " + getVisitPurpose());
        System.out.println("    HostName: " + getHostName());
    }
}
