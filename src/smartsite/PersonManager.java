package smartsite;

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

    PersonManager(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
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

    public Person findPersonByID(String id) {
        if (id == null) return null;

        for (int i = 0; i < size; i++) {
            if (people[i].getId().equalsIgnoreCase(id.trim())) {
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
    //delete
    @SuppressWarnings("empty-statement")
    public void deleteByID(String id){
     Person p = this.findPersonByID(id);
             if(p==null)
                 System.out.println("No find id");
             else {
                 int a=-1;
                 for(int i =0; i<size;i++){
                 if (people[i] == p) {
                a = i;
                break;}
             }
                 System.out.println("Id has removed");
             }
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
    } //output
    
}
   public void displayRoleDescriptions() {
    for (int i = 0; i < size; i++) {
        System.out.println(
            people[i].getRoleDescription()
        );
    }
}
}