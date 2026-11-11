package Account;

public class Account {
    private final String code;
    private final String password;

    public Account(String code, String password) {
        this.code = code;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValid() {
        return code != null && !code.trim().isEmpty()
                && !code.contains("|")
                && password != null && !password.isEmpty()
                && !password.contains("|");
    }

    public String toFileLine() {
        return code.trim() + "|" + password;
    }

    public static Account fromFileLine(String line) {
        String[] fields = line.split("\\|", 2);
        if (fields.length != 2) {
            throw new IllegalArgumentException("Account row must be code|password.");
        }
        return new Account(fields[0].trim(), fields[1]);
    }
}