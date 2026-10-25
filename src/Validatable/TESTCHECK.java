package Validatable;

import Person.PersonManager;

public class TESTCHECK {
    public static void main(String[] args) {
        // Khởi tạo PersonManager (hoặc null nếu chưa dùng)
        PersonManager pm = PersonManager.getInstance();

        System.out.println("=== TEST CHECK CODE V2 ===");

        // 1. Test cho WORKER (Prefix: WK)
        System.out.println("\n--- 1. Testing WORKER ---");
        String workerCode = CheckCode.checkCode(pm, "WORKER");
        System.out.println("-> Result Code: " + workerCode);

        // 2. Test cho SAFETYOFFICER (Prefix: SO)
        System.out.println("\n--- 2. Testing SAFETYOFFICER ---");
        String safetyOfficerCode = CheckCode.checkCode(pm, "SAFETYOFFICER");
        System.out.println("-> Result Code: " + safetyOfficerCode);
        
//         System.out.println("\n--- 3. Testing PersonId ---");
//        String personId = CheckId_v2.checkId("Person");
//        System.out.println("-> Result Code: " +personId );
//        
//        
//         System.out.println("\n--- 4. Testing PersonId ---");
//        String zoneId = CheckId_v2.checkId("Zone");
//        System.out.println("-> Result Code: " +zoneId );
    }
}