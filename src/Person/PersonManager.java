package Person;

import Validatable.CheckCode;
import Validatable.CheckId;
import Validatable.CheckName;
import Validatable.CheckPersonAttributes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import Validatable.InputScanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import ui_Dang_dang_sua.Draft;

public class PersonManager {

    /*private Person[] people;
    private int size;*/
    private final List<Person> people = new ArrayList<>();
    private static PersonManager instance;

    public PersonManager() {
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
        if (findPersonByID(p.getId()) != null) {
            return false;
        }
        people.add(p);
        return true;

    }

    private String readRequired(String message) {
        while (true) {
            System.out.print(message);
            Scanner sc = InputScanner.getScanner();
            if (!sc.hasNextLine()) {
                throw new NoSuchElementException();
            }

            String value = sc.nextLine().trim();

            if (value.isEmpty()) {
                System.out.println(Draft.center(Draft.RED + "The information can no be empty!" + Draft.BAS));
            } else if (value.contains("|")) {
                System.out.println(Draft.center(Draft.RED + "The information can not contain |" + Draft.BAS));
            } else {
                return value;
            }
        }
    }

    public void addPersonFromInput() {
        try {
            String role = chooseRole();
            if (role == null) {
                return;
            }
            String id;
            while (true) {
                id = CheckId.checkPersonId();
                if (this.findPersonByID(id) == null) {
                    break;
                }
                System.out.println(Draft.center(Draft.RED + "ID already exists! Please enter another ID." + Draft.BAS));
            }
            String name = CheckName.checkPersonName();

            String code;

            while (true) {
                code = CheckCode.checkCode(this, role);

                if (this.findPersonByCode(code) == null) {
                    break;
                }
                System.out.println(Draft.center(
                        Draft.RED + "Code already exists! Please enter another code." + Draft.BAS
                ));
            }
            Person person;
            String status = "ACTIVE";
            switch (role) {
                case "WORKER":
                    String company = CheckPersonAttributes.checkCompanyName();
                    String contract = CheckPersonAttributes.checkContractType();
                    person = new Worker(
                            id, name, code, role, status, company, contract
                    );
                    break;
                case "CONTRACTOR":
                    String contractPerson = readRequired("    ➔ Enter Person's Company: ");

                    person = new Contractor(
                            id, name, code, status, contractPerson);
                    break;

                case "VISITOR":
                    String purpose = CheckPersonAttributes.checkVisitPurpose();
                    String host = CheckName.checkHostName();

                    person = new Visitor(
                            id, name, code, role, status, purpose, host
                    );
                    break;

                case "SAFETY_OFFICER":
                    String license = CheckPersonAttributes.checkLicense();

                    person = new SafetyOfficer(
                            id, name, code, role, status, 0, license
                    );
                    break;

                case "SITE_MANAGER":
                    String department = CheckPersonAttributes.checkDepartment();

                    person = new SiteManager(
                            id, name, code, role, status, department
                    );
                    break;

                default:
                    return;
            }

            if (this.addPerson(person)) {
                System.out.println(Draft.center(Draft.GRN + "This person has been added to the list!" + Draft.BAS));
                saveToFile();
            } else {
                System.out.println(Draft.center(Draft.RED + "Added failure: invalid data or existed ID/code!" + Draft.BAS));
            }
        } catch (NoSuchElementException e) {
            System.out.println(Draft.center("Cancel the adding process!"));
        }
    }

    private String chooseRole() {
        while (true) {
            System.out.println(Draft.center("--- Enter the ROLE ---"));
            System.out.println("    [1]  Worker");
            System.out.println("    [2]  Contractor");
            System.out.println("    [3]  Visitor");
            System.out.println("    [4]  Safety Officer");
            System.out.println("    [0]  Cancel");
            System.out.println("");

            String choice = readRequired( "    ➔ Enter Person Role: " );

            switch (choice) {
                case "1":
                    return "WORKER";
                case "2":
                    return "CONTRACTOR";
                case "3":
                    return "VISITOR";
                case "4":
                    return "SAFETY_OFFICER";
                case "0":
                    return null;
                default:
                    System.out.println(Draft.center(Draft.RED + "Please enter the number between 0-4!" + Draft.BAS));
            }
        }
    }

    // ----------------------------------------------------------------
    // Tìm theo ID
    // ----------------------------------------------------------------
    public Person findPersonByID(String id) {
        if (id == null) {
            return null;
        }
        for (Person p : people) {
            if (p.getId().equalsIgnoreCase(id.trim())) {
                return p;
            }
        }
        return null;
    }

    // ----------------------------------------------------------------
    // Tìm theo Code (dùng cho login)
    // ----------------------------------------------------------------
    public Person findPersonByCode(String code) {
        if (code == null) {
            return null;
        }
        for (Person p : people) {
            if (p.getCode().equalsIgnoreCase(code.trim())) {
                return p;
            }
        }
        return null;
    }

    // ----------------------------------------------------------------
    //method goi ham tim tu id va code
    // ----------------------------------------------------------------
    public Person findPersonFromInput() {
        Scanner scanner = InputScanner.getScanner();

        int method = InputScanner.readRange(
                scanner,
                "    Search by: 1. ID / 2. Code / 3. Cancel: ",
                1, 3, // Phải là 3 vì có lựa chọn Cancel
                Draft.center(Draft.RED + "Please enter 1, 2 or 3!" + Draft.BAS)
        );
        Draft.displayMenuLine();
        if (method == 3) {
            return null;
        }
        System.out.print(method == 1
                ? "    ➔ Enter Person ID: "
                : "    ➔ Enter Person code: ");
        if (!scanner.hasNextLine()) {
            return null;
        }
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("Search value cannot be empty!");
            return null;
        }
        Person person = method == 1
                ? findPersonByID(keyword)
                : findPersonByCode(keyword);
        if (person == null) {
            System.out.println("Person not found!");
        }
        return person;
    }

    public void searchPersonFromInput() {
        Person person = findPersonFromInput();

        if (person != null) {
            person.printPersonSummary();
        }
    }

    // ----------------------------------------------------------------
    // Sắp xếp theo tên
    // ----------------------------------------------------------------
    public void sortFromInput() {
        if (people.isEmpty()) {
            System.out.println(Draft.center(Draft.RED + "The list is empty!" + Draft.BAS));
            return;
        }
        Scanner scanner = InputScanner.getScanner();
        System.out.println(Draft.center("--- SORT PERSON LIST ---"));
        System.out.println("    [1]  Sort by ID");
        System.out.println("    [2]  Sort by Role");
        System.out.println("    [3]  Sort by Role, then ID");
        System.out.println("    [4]  Original order");
        System.out.println("");
        int choice = InputScanner.readRange(scanner, Draft.BLU + "    ➔ Enter your choice: " + Draft.BAS, 1, 4,
                Draft.center(Draft.RED + "Please enter a number between 1-4!" + Draft.BAS));
        if (choice == -1) {
            return;
        }

        Comparator<String> text = Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER);
        Comparator<Person> byId = Comparator.comparing(Person::getId, text);
        Comparator<Person> byRole = Comparator.comparing(Person::getRole, text);

        List<Person> view = new ArrayList<>(people);   // bản sao, people giữ nguyên
        switch (choice) {
            case 1:
                view.sort(byId);
                break;
            case 2:
                view.sort(byRole);
                break;
            case 3:
                view.sort(byRole.thenComparing(byId));
                break;
            default:
                break;                            // 4: giữ thứ tự gốc
        }
        displayList(view);
    }

    // ----------------------------------------------------------------
    // Xóa theo ID
    // ----------------------------------------------------------------
    public void deleteByID(String id) {
        Person p = findPersonByID(id);
        if (p == null) {
            System.out.println(Draft.center(Draft.RED + "ID is not found!!!" + Draft.BAS));
            return;
        }
        people.remove(p);              // bỏ vòng dời phần tử
        System.out.println(Draft.center(Draft.GRN + "ID has removed" + Draft.BAS));
    }

    public void deletePersonFromInput() {
        Scanner sc = InputScanner.getScanner();
        Person p = findPersonFromInput();

        if (p == null) {
            return;
        }

        p.printPersonSummary();
        while (true) {
            System.out.print("    Are you sure you want to delete this Person? [Y/N]: ");

            if (!sc.hasNextLine()) {
                return;
            }

            String yn = sc.nextLine().trim();

            switch (yn.toLowerCase()) {
                case "n":
                    System.out.println(Draft.center("Cancel deleting."));
                    System.out.println(Draft.center("No one has been removed!"));
                    return;

                case "y":
                    deleteByID(p.getId());
                    return;

                default:
                    System.out.println(
                            Draft.center(Draft.RED + "Please enter Y or N!" + Draft.BAS)
                    );
            }
        }
    }

    //--------------------------
    //Update Person
    //--------------------
    public void updatePersonById() {
        String id = CheckId.checkPersonId();
        Person person = findPersonByID(id);
        if (person == null) {
            System.out.println(Draft.center(
                    Draft.RED + "Person ID [" + id + "] not found!" + Draft.BAS
            ));
            return;
        }

        displayPersonInformation(person);

        // Lấy và kiểm tra các giá trị mới trước khi sửa đối tượng
        String newName = CheckName.checkPersonName();
        String newRole = CheckPersonAttributes.checkRole();
        String newCode = CheckCode.checkCode(this, newRole);
        String newStatus = CheckPersonAttributes.checkStatus();
        Person existingCodeOwner = findPersonByCode(newCode);
        if (existingCodeOwner != null && existingCodeOwner != person) {
            System.out.println(Draft.center(
                    Draft.RED + "This code already belongs to another person!" + Draft.BAS
            ));
            return;
        }
        try {
            person.setFullName(newName);
            person.setCode(newCode);
            person.setStatus(newStatus);
            person.setRole(newRole);
            saveToFile();

            System.out.println(Draft.center(
                    Draft.GRN + "Person [" + id + "] updated successfully!" + Draft.BAS
            ));

            // Gọi nếu muốn lưu ngay:
            // saveToFile();
        } catch (IllegalArgumentException e) {
            System.out.println(Draft.center(
                    Draft.RED + "Update failed: " + e.getMessage() + Draft.BAS
            ));
        }
    }

    public void displayPersonFound() {

    }

    // ----------------------------------------------------------------
    // Hiển thị tất cả
    // ----------------------------------------------------------------
    public void displayAll() {
        if (people.isEmpty()) {
            System.out.println(Draft.center(Draft.RED + "The list is empty!" + Draft.BAS));
            return;
        }
        Draft.displayMenuLine();
        System.out.println(Draft.center("=== The list of Person(s) using S-SMARTSITE ==="));
        for (Person p : people) {
            System.out.println(p);
        }
    }

    public void displayAllByGehihi() {
        displayList(people);
    }

    private void displayList(List<Person> list) {
        if (list.isEmpty()) {
            System.out.println(Draft.center(Draft.RED + "The list is empty!" + Draft.BAS));
            return;
        }
        Draft.displayMenuLine();
        System.out.println();
        System.out.println(Draft.center("=== The list of Person(s) using S-SMARTSITE ==="));
        System.out.println();
        System.out.printf("                           %-6s | %-28s | %-15s | %-10s%n",
                "ID", "Full Name", "Role", "Status");
        System.out.println(Draft.center("----------------------------------------------------------------------"));
        for (Person p : list) {
            System.out.printf("                           %-6s | %-28s | %-15s | %-10s%n",
                    p.getId(), p.getFullName(), p.getRole(), p.getStatus());
        }
        System.out.println(Draft.center("----------------------------------------------------------------------"));
        System.out.println();
    }

    // ----------------------------------------------------------------
    // BUG-07: Đọc và parse Person.txt thành các Person object
    // ----------------------------------------------------------------
    public void loadFromFile(String fileName) throws IOException {
        try ( BufferedReader reader = Files.newBufferedReader(
                Paths.get(fileName), StandardCharsets.UTF_8)) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);
                if (data.length < 5) {
                    System.out.println("[!] Dòng " + lineNumber + " thiếu dữ liệu: " + line);
                    continue;
                }

                String role = data[0].trim();
                String id = data[1].trim();
                String fullName = data[2].trim();
                String code = data[3].trim();
                String status = data[4].trim();
                Person person;

                switch (role.toUpperCase()) {
                    case "WORKER":
                        String companyName = data.length > 5 ? data[5].trim() : "";
                        String contractType = data.length > 6 ? data[6].trim() : "";
                        person = new Worker(id, fullName, code, role, status,
                                companyName, contractType);
                        break;
                    case "CONTRACTOR":
                        // parts[5]=companyName, parts[6]=contractType
                        String contactPerson = data.length > 5 ? data[5].trim() : "";
                        String contractStatus = data.length > 6 ? data[6].trim() : "";
                        Contractor contractor = new Contractor(id, fullName, code, status, contactPerson);
                        contractor.setContractStatus(contractStatus);
                        person = contractor;
                        break;
                    case "VISITOR":
                        // parts[5]=visitPurpose, parts[6]=hostName
                        String visitPurpose = data.length > 5 ? data[5].trim() : "";
                        String hostName = data.length > 6 ? data[6].trim() : "";
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
                        System.out.println("[!] Rows " + lineNumber
                                + " Role is not identity " + role);
                        continue;
                }

                /*  if (!addPerson(person)) {
                System.out.println("[!] Không thêm được người ở dòng "
                + lineNumber + " (ID trùng?): " + id);
                }*/
            }
        }
        Draft.clearConsoleIDE();
    }

    public void displayPersonInformation(Person person) {
        if (person == null) {
            System.out.println(Draft.center(Draft.RED + "Can not find the logged in Person." + Draft.BAS));
            return;
        }
        Draft.displayMenuLine();
        System.out.println("");
        System.out.println(Draft.center("=== ACCOUNT INFORMATION ==="));
        System.out.println("");
        System.out.println("    🆔  ID    : " + person.getId());
        System.out.println("    👤  Name  : " + person.getFullName());
        System.out.println("    💼  Role  : " + person.getRole());
        System.out.println("    🟢  Status: " + Draft.GRN + person.getStatus() + Draft.BAS);
        System.out.println("");
        //person.getId(), person.getFullName(), person.getRole(), person.getStatus()
        if (person instanceof Worker) {
            Worker worker = (Worker) person;

            // Thay bằng đúng getter có trong Worker.java
            System.out.println("    Company       : " + worker.getCompanyName());
            System.out.println("    Contract type : " + worker.getContractType());
        }
    }
    private static final String DATA_FILE = "data/Person.txt";   // dùng chung cho load và save

    public boolean saveToFile() {
        Path target = Paths.get(DATA_FILE);
        Path temp = Paths.get(DATA_FILE + ".tmp");
        try {

            try ( BufferedWriter writer = Files.newBufferedWriter(temp, StandardCharsets.UTF_8)) {
                int index = 0;
                for (Person p : people) {
                    if (p == null) {
                        System.out.println("Found null element at index " + index);
                        index++;
                        continue;
                    }
                    writer.write(p.toFileLine());
                    writer.newLine();
                    index++;
                }
            }   // writer tự đóng ở đây, rồi mới move

            Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.out.println(Draft.center(Draft.RED + "Cannot save file: " + e.getMessage() + Draft.BAS));
            return false;
        }
    }
}
