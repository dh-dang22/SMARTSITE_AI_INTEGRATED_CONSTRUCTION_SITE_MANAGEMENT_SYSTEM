package Person;

public class Contractor extends Person {

    private String contactPerson;
    private String contractStatus;

    public Contractor() {
        super();
    }

    public Contractor(String id, String fullName, String code, String status, String contactPerson){

        super(id, fullName, code, "CONTRACTOR", status);
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
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
            && !isBlank(contactPerson)
            && !isBlank(contractStatus)
            && "CONTRACTOR".equals(getRole());
    }

    @Override
    public String getRoleDescription() {
        return "Contractor performs contracted work at the site.";
    }

    @Override
    public String toFileLine() {
        return super.toFileLine()
            + "|" + contactPerson
            + "|" + contractStatus;
    }

    @Override
    public String toString() {
        return super.toString()
            + " | Contact person: " + contactPerson
            + " | Contract status: " + contractStatus;
    }
}