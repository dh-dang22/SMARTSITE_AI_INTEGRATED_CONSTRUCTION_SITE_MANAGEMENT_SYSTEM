/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarttite;

/**
 *
 * @author Minh_Khang
 */
public class Person {

    private String id;
    private String fullName;
    private String code;
    private String role;
    private String status;

    public Person() {
    }

    public Person(String id, String fullName, String code, String role, String status) {
        this.id = id;
        this.fullName = fullName;
        this.code = code;
        this.role = role;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (isBlank(id)) {
            throw new IllegalArgumentException("Person id cannot be empty.");
        }
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
         if (isBlank(fullName)) {
            throw new IllegalArgumentException("Full name cannot be empty.");
        }
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(status); // nho AI ho tro
    }

    public String toFileLine() {
        return role + "|" + id + "|" + fullName + "|" + code + "|" + status;
    }

    @Override
    public String toString() {
        return id + " - " + fullName + " - " + role + " - " + status;
    }
    private static boolean isBlank(String value) {
    return value == null || value.trim().isEmpty();
}
}Click