/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartsite;

/**
 *
 * @author Minh_Khang
 */
public class Worker extends Person {

    private String companyName;
    private String contractType;

    public Worker(String id, String fullName, String code, String role, String status,
            String companyName, String contractType) {
        super(id, fullName, code, role, status);
        this.companyName = companyName;
        this.contractType = contractType;
    }

    // Constructor rong
    public Worker(String id, String fullName, String code, String role, String status) {
        this(id, fullName, code, role, status, "", "");
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @Override
    public String getRoleDescription() {
        return "Worker can check in, check out, and view own attendance history.";
    }

    @Override
    public boolean isValid() {
        return super.isValid() && !isBlank(companyName) && !isBlank(contractType);
    }

    @Override
    public String toString() {
        return super.toString() + " | " + companyName + " | " + contractType;
    }
}