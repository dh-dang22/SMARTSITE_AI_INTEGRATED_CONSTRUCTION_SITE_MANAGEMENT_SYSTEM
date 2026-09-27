package smarttite;

import java.util.ArrayList;
import java.util.List;

/**
 * Quản lý danh sách người dùng (Person, Worker, Visitor, etc.)
 *
 * @author Minh_Khang
 */
public class PersonManager {

    private final List<Person> people;

    public PersonManager() {
        this.people = new ArrayList<>();
    }

    public PersonManager(List<Person> people) {
        this.people = people != null ? people : new ArrayList<>();
    }

    public boolean addPerson(Person p) {
        if (p == null || p.getId() == null || p.getId().trim().isEmpty()) {
            return false;
        }
        if (findPersonById(p.getId()) != null) {
            System.out.println("Lỗi: ID " + p.getId() + " đã tồn tại!");
            return false;
        }
        people.add(p);
        return true;
    }

    public Person findPersonById(String id) {
        if (id == null) return null;
        for (Person p : people) {
            if (p.getId() != null && p.getId().equalsIgnoreCase(id.trim())) {
                return p;
            }
        }
        return null;
    }

    public Person findPersonByName(String fullName) {
        if (fullName == null) return null;
        for (Person p : people) {
            if (p.getFullName() != null && p.getFullName().equalsIgnoreCase(fullName.trim())) {
                return p;
            }
        }
        return null;
    }

    public Person findPersonByCode(String code) {
        if (code == null) return null;
        for (Person p : people) {
            if (p.getCode() != null && p.getCode().equalsIgnoreCase(code.trim())) {
                return p;
            }
        }
        return null;
    }

    public Person login(String code) {
        Person p = findPersonByCode(code);
        if (p != null && p.isActive()) {
            return p;
        }
        return null;
    }

    public boolean removePersonById(String id) {
        Person p = findPersonById(id);
        if (p != null) {
            people.remove(p);
            return true;
        }
        return false;
    }

    public List<Person> getAllPeople() {
        return new ArrayList<>(people);
    }

    public List<Person> getPeopleByRole(String role) {
        List<Person> result = new ArrayList<>();
        if (role == null) return result;
        for (Person p : people) {
            if (p.getRole() != null && p.getRole().equalsIgnoreCase(role.trim())) {
                result.add(p);
            }
        }
        return result;
    }

    public boolean updatePerson(String id, String newFullName, String newStatus) {
        Person p = findPersonById(id);
        if (p != null) {
            if (newFullName != null && !newFullName.trim().isEmpty()) {
                p.setFullName(newFullName);
            }
            if (newStatus != null && !newStatus.trim().isEmpty()) {
                p.setStatus(newStatus);
            }
            return true;
        }
        return false;
    }

    public void displayAll() {
        if (people.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        System.out.println("=== DANH SÁCH NGƯỜI DÙNG SMARTSITE ===");
        for (Person p : people) {
            System.out.println(p.toString());
        }
    }
}
