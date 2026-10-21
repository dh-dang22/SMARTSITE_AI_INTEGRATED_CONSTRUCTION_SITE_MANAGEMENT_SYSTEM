package ui_Dang_dang_sua;
import Person.Person;
import Person.PersonManager;
import Validatable.CheckPassword;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Minh_Khang
 */
public class Login {
    
    private final PersonManager pm;
    private final CheckPassword checker;

    public Login(PersonManager pm, CheckPassword checker) {
        this.pm = pm;
        this.checker = checker;
    }

    public Person login(String code, String password) {
        if (code == null || code.trim().isEmpty()
                || password == null || password.isEmpty()) {
            return null;
        }

        if (!checker.check(code, password)) {
            return null;
        }

        // Hàm này đã kiểm tra code và trạng thái ACTIVE.
       Person person = pm.findPersonByCode(code);

        if (person == null || !person.isActive()) {
            return null;
        }

        return person;
    }
}
