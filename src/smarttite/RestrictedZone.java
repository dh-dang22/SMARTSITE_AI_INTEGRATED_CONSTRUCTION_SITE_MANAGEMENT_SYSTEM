/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarttite;

/**
 *
 * @author GIA HUY
 */
public class RestrictedZone extends Zone {
    private int accessLevel ; 
    private String pinCode ; 

    public RestrictedZone() {
    }

    public RestrictedZone(int accessLevel, String pinCode) {
        this.accessLevel = accessLevel;
        this.pinCode = pinCode;
    }

    public RestrictedZone( String zoneId, String zoneName, String zoneDetails, int accessLevel, String pinCode) {
        super(zoneId, zoneName, zoneDetails);
        this.accessLevel = accessLevel;
        this.pinCode = pinCode;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setAccessLevel(int accessLevel) {
        if (accessLevel < 0 ){
           throw new IllegalArgumentException("Access level must be greater than or equal to 0!");
        }
         this.accessLevel = accessLevel ; 

    }

    public void setPinCode(String pinCode) {
        if (pinCode.isEmpty()){
            System.out.println("Pin Code can not be empty!");
        }
        this.pinCode = pinCode;
    }   
    
}
