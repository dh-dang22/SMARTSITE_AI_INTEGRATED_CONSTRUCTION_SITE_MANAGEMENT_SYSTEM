/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartsite;

/**
 *
 * @author GIA HUY
 */


public class SafetyOfficer extends Person {

   // private int accessLevel;         
    private String inspectionLicense; 

    public SafetyOfficer() {
        super();
    }

    public SafetyOfficer(String id, String fullName, String code, String role, String status, int accessLevel, String inspectionLicense) {
        super(id, fullName, code, role, status);
      //  this.accessLevel = accessLevel;
        this.inspectionLicense = inspectionLicense;
    }

   
  public SafetyOfficer(String id, String fullName, String code, String status, int accessLevel, String inspectionLicense) {
    this(id, fullName, code, "SAFETY_OFFICER", status, accessLevel, inspectionLicense);
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

    public String getInspectionLicense() {
        return inspectionLicense;
    }

    public void setInspectionLicense(String inspectionLicense) {
        this.inspectionLicense = inspectionLicense;
    }


    @Override
    public String getRoleDescription() {
        return "Safety Officer is responsible for safety audits, inspection, and incident logging." /* (Access Level: " + accessLevel +")."*/ ;
    }
    
    @Override
    public String toFileLine() {
        return super.toFileLine() /*+ "|" + accessLevel + "|"*/ + inspectionLicense;
    }

    @Override
    public String toString() {
        return super.toString() + /*" | Access Level: " + accessLevel +*/ " | License: " + inspectionLicense;
    }
}
    