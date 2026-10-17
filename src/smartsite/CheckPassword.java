package smartsite;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Lưu và kiểm tra mật khẩu theo code nhân sự.
 * Đọc từ file accounts.txt (format: code|password)
 *
 * @author Minh_Khang
 */
public class CheckPassword {

    private static final int MAX_SIZE = 50;
    private String[] codes= new String[MAX_SIZE];
    private String[] passwords = new String[MAX_SIZE];
    private int size = 0;
    public CheckPassword() {
    }
    // Nạp dữ liệu từ file accounts.txt
    // Format mỗi dòng: code|password
        public void loadFromFile(String fileName) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(fileName), StandardCharsets.UTF_8)) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|", 2);
                if (parts.length != 2) {
                    System.out.println("[!] Dòng account không hợp lệ: " + line);
                    continue;
                }

                boolean added = addAccount(parts[0].trim(), parts[1].trim());
                if (!added) {
                    System.out.println("[!] Không thể nạp account: " + parts[0]);
                }
            }
        }
    }

    // ----------------------------------------------------------------
    // Thêm 1 cặp code-password vào bộ nhớ
    // ----------------------------------------------------------------
    private boolean addAccount(String code, String password) {
        if (code == null || code.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        if (size >= MAX_SIZE) return false;

        // Không cho thêm code trùng
        for (int i = 0; i < size; i++) {
            if (codes[i].equalsIgnoreCase(code)) return false;
        }

        codes[size]     = code;
        passwords[size] = password;
        size++;
        return true;
    }

    // ----------------------------------------------------------------
    // Kiểm tra code + password có khớp không
    // ----------------------------------------------------------------
    public boolean check(String code, String password) {
        if (code == null || password == null) return false;

        for (int i = 0; i < size; i++) {
            if (codes[i].equalsIgnoreCase(code.trim())
                    && passwords[i].equals(password.trim())) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }
}