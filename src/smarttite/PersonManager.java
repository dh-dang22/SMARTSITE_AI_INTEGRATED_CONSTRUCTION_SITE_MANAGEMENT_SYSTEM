package smarttite;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * Quản lý danh sách người dùng (Person, Worker, Visitor, etc.)
 *
 * @author Minh_Khang
 */
public class PersonManager {
    private Person[] people;
    private int size;

    public PersonManager() {
        people = new Person[10];
        size = 0;
    }
    public boolean addPerson(Person p) {
    if (p == null || p.getid() == null || p.getid().trim().isEmpty()) {
        return false;
    }

    if (size >= people.length) {
        return false;
    }

    if (findPersonByID(p.getid()) != null) {
        return false;
    }

    people[size] = p;
    size++;
    return true;
}

    public Person findPersonByID(String id) {
        if (id == null) return null;

        for (int i = 0; i < size; i++) {
            if (people[i].getid().equalsIgnoreCase(id.trim())) {
                return people[i];
            }
        }

        return null;
    }

    public boolean sortByName() {
        if (size <= 1) {
            return false;
        }

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
    public Person login(String code){
    if(code==null || code.trim().isEmpty()){
        return null;
    }
    for(int i=0; i<size ;i++){
        if (people[i].getCode().equalsIgnoreCase(code.trim())&&people[i].isActive())
                return people[i];
            }
    return null;
    }
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
}