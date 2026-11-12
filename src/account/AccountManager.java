package Account;

import Person.Person;
import Person.PersonManager;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages code/password pairs stored in an account text file.
 */
public class AccountManager {

    private final Path file;
    private final Map<String, Account> accounts = new LinkedHashMap<>();

    public AccountManager() throws IOException {
        this("data/accounts.txt");
    }

    public AccountManager(String fileName) throws IOException {
        this.file = Paths.get(fileName);
        loadFromFile();
    }

    private void loadFromFile() throws IOException {
        accounts.clear();
        if (!Files.exists(file)) {
            Files.createDirectories(file.getParent());
            Files.createFile(file);
            return;
        }
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            try {
                Account account = Account.fromFileLine(line);

                if (account.isValid()) {
                    accounts.put(account.getCode(), account);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Bỏ qua dòng account không hợp lệ: " + line);
            }
        }
    }

    public boolean savePassword(String code, String password,
            PersonManager personManager) throws IOException {
        if (code == null || password == null || password.isEmpty()
                || personManager == null) {
            return false;
        }

        // Check that the entered code belongs to a person in Person.txt.
        Person person = personManager.findPersonByCode(code.trim());
        if (person == null || person.getCode() == null
                || person.getCode().trim().isEmpty()) {
            return false;
        }

        String storedCode = person.getCode().trim();
        Account account = new Account(storedCode, password);
        if (!account.isValid()) {
            return false;
        }

        removeCodeIgnoreCase(storedCode);
        accounts.put(storedCode, account);
        saveToFile();
        return true;
    }

    public boolean deleteAccount(String code) throws IOException {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }

        boolean removed = removeCodeIgnoreCase(code.trim());
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    private boolean removeCodeIgnoreCase(String code) {
        String keyToRemove = null;
        for (String existingCode : accounts.keySet()) {
            if (existingCode.equalsIgnoreCase(code)) {
                keyToRemove = existingCode;
                break;
            }
        }
        if (keyToRemove == null) {
            return false;
        }
        accounts.remove(keyToRemove);
        return true;
    }

    private void saveToFile() throws IOException {
        Files.createDirectories(file.getParent());

        List<String> lines = new ArrayList<>();
        for (Account account : accounts.values()) {
            lines.add(account.toFileLine());
        }

        Files.write(file, lines, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
    }

    public boolean hasAccount(String code) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        for (String existingCode : accounts.keySet()) {
            if (existingCode.equalsIgnoreCase(code.trim())) {
                return true;
            }
        }
        return false;
    }
    public Account getPassword(String code) {
    if (code == null || code.trim().isEmpty()) {
        return null;
    }

    for (Map.Entry<String, Account> entry : accounts.entrySet()) {
        if (entry.getKey().equalsIgnoreCase(code.trim())) {
            return entry.getValue();
        }
    }

    return null;
 }
}
