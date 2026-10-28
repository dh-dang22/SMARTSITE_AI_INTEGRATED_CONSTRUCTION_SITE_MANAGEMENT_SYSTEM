/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui_Dang_dang_sua;

import Person.Person;

public final class TempSession {
    private static Person currentUser;

    private TempSession() {
    }

    public static void setCurrentUser(Person person) {
        currentUser = person;
    }

    public static Person getCurrentUser() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }
}