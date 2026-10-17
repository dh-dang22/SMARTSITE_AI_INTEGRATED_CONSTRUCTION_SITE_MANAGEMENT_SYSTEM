/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import java.util.Scanner;
import ui_Dang_dang_sua.Draft;

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
    // 1. Nhập dữ liệu và trả về đối tượng Worker.
public Worker inputWorker(java.util.Scanner sc) {
    System.out.print("Nhap ID: ");
    String id = sc.nextLine().trim();

    System.out.print("Nhap ten cong nhan: ");
    String name = sc.nextLine().trim();

    System.out.print("Nhap code: ");
    String code = sc.nextLine().trim();

    System.out.print("Nhap ten cong ty: ");
    String company = sc.nextLine().trim();

    System.out.print("Nhap loai hop dong: ");
    String contract = sc.nextLine().trim();

    return new Worker(
        id, name, code, "WORKER", "ACTIVE", company, contract
    );
}

// 2. Kết hợp nhập, kiểm tra và thêm vào danh sách.
public void addWorker(java.util.Scanner sc) {
    Worker worker = inputWorker(sc);

    if (!worker.isValid()) {
        System.out.println("Thong tin khong hop le!");
        return;
    }

    if (addPerson(worker)) {
        System.out.println("Them cong nhan thanh cong!");
    } else {
        System.out.println("Khong the them cong nhan, vui long kiem tra ID/code!");
    }
}
}
