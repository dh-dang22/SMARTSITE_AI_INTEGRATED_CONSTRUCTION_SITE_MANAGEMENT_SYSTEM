import Person.Worker;
import Person.PersonManager;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import smartsite.CheckPassword;
import ui_Dang_dang_sua.Login;

/** Chạy từ thư mục gốc: java -cp build/classes LoginTest */
public class LoginTest {
    private static int passed;

    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
        passed++;
        System.out.println("PASS: " + description);
    }

    public static void main(String[] args) throws Exception {
        PersonManager people = new PersonManager();
        people.loadFromFile("data/Person.txt");
        CheckPassword passwords = new CheckPassword();
        passwords.loadFromFile("data/accounts.txt");
        Login login = new Login(people, passwords);

        check(login.login("W001", "123456") == people.findPersonByCode("W001"),
                "Valid credentials return the matching Person");
        check(login.login(" w001 ", "123456") != null, "Code ignores case and surrounding spaces");
        check(login.login("W001", "wrong") == null, "Wrong password is rejected");
        check(login.login("W001", "abc123") == null, "Another account's password is rejected");
        check(login.login("V001", "ABC123") == null, "Password is case sensitive");
        check(login.login("W001", " 123456 ") == null, "Password must match exactly");
        check(login.login("UNKNOWN", "123456") == null, "Unknown code is rejected");
        check(login.login("C001", "123456") == null, "Person without credentials is rejected");
        check(login.login(null, "123456") == null && login.login("W001", null) == null
                && login.login("", "") == null, "Null and empty input are rejected");
        check("VISITOR".equals(login.login("V001", "abc123").getRole()), "Visitor role is preserved");
        check("SAFETY_OFFICER".equals(login.login("S001", "safety123").getRole()), "Safety officer role is preserved");
        check("SITE_MANAGER".equals(login.login("M001", "manager123").getRole()), "Site manager role is preserved");

        Path fixture = Files.createTempFile("smartsite-login-", ".txt");
        try {
            Files.write(fixture, Arrays.asList("I001|inactive123", "X001|orphan123"), StandardCharsets.UTF_8);
            passwords.loadFromFile(fixture.toString());
            people.addPerson(new Worker("P900", "Inactive User", "I001", "WORKER", "INACTIVE", "BuildCo", "Full-time"));
            check(login.login("I001", "inactive123") == null, "Inactive person is rejected despite matching credentials");
            check(login.login("X001", "orphan123") == null, "Credentials without a Person are rejected");
            check(login.login("W001", "123456") == null, "Reload removes old credentials");
            passwords.loadFromFile(fixture.toString());
            check(passwords.getSize() == 2, "Reload does not append duplicate accounts");
        } finally {
            Files.deleteIfExists(fixture);
        }
        System.out.println("Passed " + passed + " login checks.");
    }
}
