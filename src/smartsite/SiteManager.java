/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartsite;

/**
 *
 * @author GIA HUY
 */

    public class SiteManager extends Person {

    private String department;
 //   private int accessLevel;  

    public SiteManager() {
        super();
    }

    public SiteManager(String id, String fullName, String code, String role, String status, String department /*, int accessLevel*/) {
        super(id, fullName, code, role, status);
        this.department = department;
        //this.setAccessLevel(accessLevel);
    }

  public SiteManager(String id, String fullName, String code, String status) {
    this(id, fullName, code, "SITE_MANAGER", status, "Operations"/*, 1*/); //[cite: 8]
}

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

//    public int getAccessLevel() {
//        return accessLevel;
//    }

//    public final void setAccessLevel(int accessLevel) {
//        if (accessLevel < 0 || accessLevel > 2) {
//            throw new IllegalArgumentException("Access level must be between 0 and 2!");
//        }
//        this.accessLevel = accessLevel;
//    }


    @Override
    public String getRoleDescription() {
        return "Site Manager oversees site operations, manages zone access, and approves safety logs." /*(Access Level: " + accessLevel + ")."*/;
    }
    
    @Override
    public String toFileLine() {
        return super.toFileLine() + "|" + department /*+ "|" + accessLevel*/;
    }

    @Override
    public String toString() {
        return super.toString() + " | Deparment: " + department /*+ " | Access Level: " + accessLevel*/;
    }
}

