/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Zone;

/**
 *
 * @author GIA HUY
 */
public class RestrictedZone extends Zone {

    private String pinCode;

    public RestrictedZone() {
        super();
    }

    public RestrictedZone(String zoneId, String zoneName, String zoneDetails, boolean isSaved, String pinCode) {
        super(true, zoneId, zoneName, zoneDetails, isSaved);
        this.setPinCode(pinCode);
    }

    public String getPinCode() {
        return pinCode;
    }

    public final void setPinCode(String pinCode) {
        if (pinCode.isEmpty()) {
            System.out.println("Pin Code can not be empty!");
        }
        this.pinCode = pinCode;
    }

    @Override
    public boolean isAccessRestricted() {
        return true;
    }

    @Override
    public boolean isValid() {
        return super.isValid() && (pinCode != null && pinCode.trim().matches("^[A-Z]{3}\\d{3}$"));
    }

    @Override
    public String toString() {
        return super.toString() + "; PinCode : " + pinCode;
    }

    @Override
    public String toFileString(){
        return super.toFileString() + "|" + pinCode;
    }
}