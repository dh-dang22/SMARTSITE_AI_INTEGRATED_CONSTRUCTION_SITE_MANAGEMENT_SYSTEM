package Person;

public class Contractor extends Person {

    private String company;
    private String contractStatus;

    public Contractor() {
        super();
    }

    public Contractor(String id, String fullName, String code, String status, String contactPerson){

        super(id, fullName, code, "CONTRACTOR", status);
        this.company = contactPerson;
    }

    public String getContactPerson() {
        return company;
    }

    public void setContactPerson(String contactPerson) {
        this.company = contactPerson;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    @Override
    public boolean isValid() {
        return super.isValid()
            && !isBlank(company)
            && !isBlank(contractStatus)
            && "CONTRACTOR".equals(getRole());
    }

    @Override
    public String toFileLine() {
        return super.toFileLine()
            + "|" + company
            + "|" + contractStatus;
    }

    @Override
    public String toString() {
        return super.toString()
            + " | Contact person: " + company
            + " | Contract status: " + contractStatus;
    }

    @Override
    protected void printRoleDetails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}