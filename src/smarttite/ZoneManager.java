/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarttite;

/**
 *
 * @author Minh_Khang
 */
public class ZoneManager {
    private final Zone[] area;
    private int size;

    public ZoneManager() {
        area = new Zone [10];
        size  =0;
    }
    public boolean addZone(Zone z){
        if(z==null || z.getZoneId() == null || z.getZoneId().isEmpty()){
        return false;
    }
        if (size >area.length){
        return false;
    }
    area[size]=z;
    size ++;
    return true;
    }
    
}
