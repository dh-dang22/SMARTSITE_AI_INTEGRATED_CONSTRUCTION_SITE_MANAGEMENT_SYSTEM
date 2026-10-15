package smartsite;

public class TestCaseRunner {
public interface Validatable {
    boolean isValid();
}
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("          RUNNING LAB TEST CASES (PRO192 - SMARTSITE)          ");
        System.out.println("===============================================================\n");

        // --- TC01: Instantiate subclass ---
        System.out.print("[TC01] Create Worker: ");
        Worker w1 = new Worker("P001", "Tran Minh Khang", "W001", "WORKER", "ACTIVE", "BuildCo", "Full-time");
        if (w1 !=null && w1 instanceof Worker) {
            System.out.println("PASSED -> Worker object created: " + w1.getFullName());
        } else {
            System.out.println("FAILED");
        }

        // --- TC02: Constructor chaining (Worker calls super) ---
        System.out.print("[TC02] Constructor chaining (super): ");
        if ("P001".equals(w1.getId()) && "Tran Minh Khang".equals(w1.getFullName())) {
            System.out.println("PASSED -> Common fields (id, name) initialized via super(...)");
        } else {
            System.out.println("FAILED");
        }

        // --- TC03: Inherited method (Worker calls isActive) ---
        System.out.print("[TC03] Inherited method (isActive): ");
        if (w1.isActive()) {
            System.out.println("PASSED -> isActive() called successfully from Person");
        } else {
            System.out.println("FAILED");
        }

        // --- TC04: Overriding Worker (getRoleDescription) ---
        System.out.print("[TC04] Overriding Worker getRoleDescription: ");
        String wDesc = w1.getRoleDescription();
        if (wDesc.contains("Worker")) {
            System.out.println("PASSED -> " + wDesc);
        } else {
            System.out.println("FAILED");
        }

        // --- TC05: Overriding Visitor (getRoleDescription) ---
        System.out.print("[TC05] Overriding Visitor getRoleDescription: ");
        Visitor v1 = new Visitor("P002", "Bach Gia Huy", "V001", "VISITOR", "ACTIVE", "Site Visit", "Manager A");
        String vDesc = v1.getRoleDescription();
        if (vDesc.contains("Visitor")) {
            System.out.println("PASSED -> " + vDesc);
        } else {
            System.out.println("FAILED");
        }

        // --- TC06: Polymorphism reference (Person p = new Worker) ---
        System.out.print("[TC06] Polymorphism reference: ");
        Person pPoly = new Worker("P003", "Nguyen Van A", "W002", "WORKER", "ACTIVE", "FPT", "Part-time");
        // Gọi hàm bị override qua con trỏ lớp cha
        if (pPoly.getRoleDescription().contains("Worker")) {
            System.out.println("PASSED -> Subclass method invoked via Person reference");
        } else {
            System.out.println("FAILED");
        }

        // --- TC07: Polymorphism array (Person[] loop) ---
        System.out.print("[TC07] Polymorphism array: ");
        Person[] list = new Person[2];
        list[0] = w1;
        list[1] = v1;
        boolean polyArrayOk = true;
        for (Person p : list) {
            if (p.getRoleDescription() == null) polyArrayOk = false;
        }
        if (polyArrayOk) {
            System.out.println("PASSED -> Looped through Person[], correct subclass methods executed");
        } else {
            System.out.println("FAILED");
        }

        // --- TC08: Interface validation (isValid) ---
        
        System.out.print("[TC08] Interface validation (isValid): ");
        if (w1.isValid()) {
            System.out.println("PASSED -> Valid object returns true correctly");
        } else {
            System.out.println("FAILED");
        }
        

        // --- TC09: Common validation (Empty id) ---
        System.out.print("[TC09] Common validation (Empty id): ");
        Worker invalidIdWorker = new Worker("", "No ID", "W003", "WORKER", "ACTIVE", "BuildCo", "Full-time");
        if (!invalidIdWorker.isValid()) {
            System.out.println("PASSED -> Empty ID rejected (isValid returns false)");
        } else {
            System.out.println("FAILED");
        }

        // --- TC10: Subclass validation (Empty company name) ---
        System.out.print("[TC10] Subclass validation (Empty company): ");
        Worker invalidCompanyWorker = new Worker("P004", "No Company", "W004", "WORKER", "ACTIVE", "", "Full-time");
        if (!invalidCompanyWorker.isValid()) {
            System.out.println("PASSED -> Empty company name rejected (isValid returns false)");
        } else {
            System.out.println("FAILED");
        }

        System.out.println("\n===============================================================");
        System.out.println("                    ALL TEST CASES COMPLETED                   ");
        System.out.println("===============================================================");
    }
}