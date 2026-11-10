package ui_Dang_dang_sua;


import Incident.IncidentManager;
import Zone.Zone;
import java.util.Scanner;
import Zone.ZoneManager2;

public class WelcomeMenu {

    public void displayWelcomeMenu() {
        Scanner sc = Validatable.InputScanner.getScanner();
        ZoneManager2 zm = ZoneManager2.getInstance();
        IncidentManager im = IncidentManager.getInstance();
        Zone activeZone = zm.getRandomActiveZone();
        boolean running = true;
        
       
        while (true) {

            if (im.hasInProgressIncident(activeZone.getZoneId()) && activeZone.isRestricted()){
                Draft.displayBanner();
                Draft.displayIncidentInProgress(activeZone);
                Draft.displayBannerRES(activeZone);
                Draft.clearConsoleIDE();
                Draft.displayBanner();
                Draft.displayCurrentZone(activeZone);
                int choice = Draft.inputAction(sc);
                Draft.doAction(choice, zm, running);
            } else if (im.hasInProgressIncident(activeZone.getZoneId()) && !activeZone.isRestricted()){
                Draft.displayBanner();
                Draft.displayIncidentInProgress(activeZone);
                Draft.displayCurrentZone(activeZone);
                int choice = Draft.inputAction(sc);
                Draft.doAction(choice, zm, running);            
            } else if (!im.hasInProgressIncident(activeZone.getZoneId()) && activeZone.isRestricted()){
                Draft.displayBanner();
                Draft.displayBannerRES(activeZone);
                Draft.clearConsoleIDE();
                Draft.displayBanner();
                Draft.displayCurrentZone(activeZone);
                int choice = Draft.inputAction(sc);
                Draft.doAction(choice, zm, running);
            } else {
                Draft.displayBanner();
                Draft.displayCurrentZone(activeZone);
                int choice = Draft.inputAction(sc);
                Draft.doAction(choice, zm, running);
            }
            
        } //while (running)

    } //displayWelcomeMenu()

}//class
