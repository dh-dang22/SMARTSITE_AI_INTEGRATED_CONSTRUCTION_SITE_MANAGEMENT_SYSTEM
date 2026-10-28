package ui_Dang_dang_sua;

import Attendance.AttendanceManager;
import Incident.IncidentManager;
import Zone.ZoneManager2;

/** Shared data for the current application session. */
final class MenuSession {
    static final AttendanceManager ATTENDANCE = new AttendanceManager();
    static final ZoneManager2 ZONES = new ZoneManager2();
    static final IncidentManager INCIDENTS = new IncidentManager();
    private MenuSession() { }
}
