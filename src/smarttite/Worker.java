/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package smarttite;

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

    // Constructor ngắn, khớp với dòng new Worker(...) trong Main
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
    public String toString() {
        return super.toString() + " - " + companyName + " - " + contractType;
    }
}