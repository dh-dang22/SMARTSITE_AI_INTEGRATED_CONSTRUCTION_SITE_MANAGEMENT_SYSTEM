package Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import Validatable.InputScanner;



/**
 * Quản lý danh sách người dùng (Person, Worker, Visitor, etc.)
 *
 * @author Minh_Khang
 */
public class PersonManager {
    private Person[] people;
    private int size;
    private static PersonManager instance;

    public PersonManager() {
        people = new Person[10];
        size = 0;
    }

    // ----------------------------------------------------------------
    // Singleton — lấy instance duy nhất của PersonManager
    // ----------------------------------------------------------------
    public static PersonManager getInstance() {
        if (instance == null) {
            instance = new PersonManager();
        }
        return instance;
    }

    // ----------------------------------------------------------------
    // Thêm người vào danh sách
    // ----------------------------------------------------------------
     public boolean addPerson(Person p) {
        if (p == null || p.getId() == null || p.getId().trim().isEmpty()) {
            return false;
        }

        if (size >= people.length) {
            Person[] temp = new Person[people.length * 2];
            System.arraycopy(people, 0, temp, 0, people.length);
            people = temp;
        }

        if (findPersonByID(p.getId()) != null) {
            return false;
        }

        people[size] = p;
        size++;
        return true;
    }
     
    public void addPersonFromInput() {
    try {
        ConditionPerson input = new ConditionPerson();
        Person person = input.inputPerson();

        if (person == null) {
            System.out.println("Da huy them Person.");
            return;
        }

        if (addPerson(person)) {
    System.out.println("Da them Person vao bo nho!");
        } else {
    System.out.println("Them that bai: ID rong hoac da ton tai!");
}
    } catch (java.util.NoSuchElementException e) {
        System.out.println("Da dung nhap du lieu.");
    }
    }
    // ----------------------------------------------------------------
    // Tìm theo ID
    // ----------------------------------------------------------------
    public Person findPersonByID(String id) {
        if (id == null) return null;

        for (int i = 0; i < size; i++) {
            if (people[i].getId().equalsIgnoreCase(id.trim())) {
                return people[i];
            }
        }
        return null;
    }

    // ----------------------------------------------------------------
    // Tìm theo Code (dùng cho login)
    // ----------------------------------------------------------------
    public Person findPersonByCode(String code) {
        if (code == null) return null;

        for (int i = 0; i < size; i++) {
            if (people[i].getCode().equalsIgnoreCase(code.trim())) {
                return people[i];
            }
        }
        return null;
    }

    // ----------------------------------------------------------------
    // Sắp xếp theo tên
    // ----------------------------------------------------------------
    public boolean sortByName() {
        if (size <= 1) return false;

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (people[j].getFullName().compareToIgnoreCase(people[j + 1].getFullName()) > 0) {
                    Person temp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = temp;
                }
            }
        }
        return true;
    }

    // ----------------------------------------------------------------
    // Xóa theo ID
    // ----------------------------------------------------------------
    public void deleteByID(String id) {
        Person p = this.findPersonByID(id);
        if (p == null) {
            System.out.println("No find id");
            return;
        }

        int index = -1;
        for (int i = 0; i < size; i++) {
            if (people[i] == p) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Dời các phần tử sau index lên 1 vị trí
            for (int i = index; i < size - 1; i++) {
                people[i] = people[i + 1];
            }
            people[size - 1] = null;
            size--;
            System.out.println("Id has removed");
        }
    }
        public void deletePersonFromInput() {
         Scanner scanner = InputScanner.getScanner();

         System.out.print("Nhap ID nguoi can xoa: ");

         if (!scanner.hasNextLine()) {
             return;
         }

         String id = scanner.nextLine().trim();

         if (id.isEmpty()) {
             System.out.println("ID khong duoc de trong!");
             return;
         }

         deleteByID(id);
     }

    //--------------------------
        // Tìm theo ID
    //--------------------
        public void findPersonFromInput() {
        Scanner scanner = InputScanner.getScanner();
        int method = InputScanner.readRange(scanner,
            "Search by: 1. ID / 2. Code: ", 1, 2, "Enter 1 or 2.");
        if (method == -1) return;
        System.out.print(method == 1 ? "Enter Person ID: " : "Enter Person code: ");
        if (!scanner.hasNextLine()) return;
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("Search value cannot be empty.");
            return;
        }
        Person person = method == 1 ? findPersonByID(keyword) : findPersonByCode(keyword);
        if (person == null) System.out.println("Person not found.");
        else System.out.println(person);
    }
    // ----------------------------------------------------------------
    // Hiển thị tất cả
    // ----------------------------------------------------------------
    public void displayAll() {
        if (size == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        System.out.println("=== DANH SACH NGUOI DUNG SMARTSITE ===");
        for (int i = 0; i < size; i++) {
            System.out.println(people[i]);
        }
    }

    public void displayRoleDescriptions() {
        for (int i = 0; i < size; i++) {
            System.out.println(people[i].getRoleDescription());
        }
    }

    // ----------------------------------------------------------------
    // BUG-07: Đọc và parse Person.txt thành các Person object
    //
    // Format từng dòng trong Person.txt:
    //   WORKER        → WORKER|ID|FullName|Code|Status|CompanyName|ContractType
    //   CONTRACTOR    → CONTRACTOR|ID|FullName|Code|Status|CompanyName|ContractType
    //   VISITOR       → VISITOR|ID|FullName|Code|Status|VisitPurpose|HostName
    //   SAFETY_OFFICER→ SAFETY_OFFICER|ID|FullName|Code|Status|License
    //   SITE_MANAGER  → SITE_MANAGER|ID|FullName|Code|Status|Department
    // ----------------------------------------------------------------
    public void loadFromFile(String fileName) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(fileName), StandardCharsets.UTF_8)) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                String[] data = line.split("\\|", -1);
                if (data.length < 5) {
                    System.out.println("[!] Dòng " + lineNumber + " thiếu dữ liệu: " + line);
                    continue;
                }

                String role     = data[0].trim();
                String id       = data[1].trim();
                String fullName = data[2].trim();
                String code     = data[3].trim();
                String status   = data[4].trim();
                Person person;

                switch (role.toUpperCase()) {
                    case "WORKER":
                    case "CONTRACTOR":
                        // parts[5]=companyName, parts[6]=contractType
                        String companyName   = data.length > 5 ? data[5].trim() : "";
                        String contractType  = data.length > 6 ? data[6].trim() : "";
                        person = new Worker(id, fullName, code, role, status,
                                            companyName, contractType);
                        break;

                    case "VISITOR":
                        // parts[5]=visitPurpose, parts[6]=hostName
                        String visitPurpose = data.length > 5 ? data[5].trim() : "";
                        String hostName     = data.length > 6 ? data[6].trim() : "";
                        person = new Visitor(id, fullName, code, role, status,
                                             visitPurpose, hostName);
                        break;

                    case "SAFETY_OFFICER":
                        // parts[5]=inspectionLicense
                        String license = data.length > 5 ? data[5].trim() : "";
                        person = new SafetyOfficer(id, fullName, code, role, status,
                                                   0, license);
                        break;

                    case "SITE_MANAGER":
                        // parts[5]=department
                        String department = data.length > 5 ? data[5].trim() : "";
                        person = new SiteManager(id, fullName, code, role, status,
                                                 department);
                        break;

                    default:
                        System.out.println("[!] Dòng " + lineNumber
                                + " có role không xác định: " + role);
                        continue;
                }

                if (!addPerson(person)) {
                    System.out.println("[!] Không thêm được người ở dòng "
                            + lineNumber + " (ID trùng?): " + id);
                }
            }
        }
    }
}
