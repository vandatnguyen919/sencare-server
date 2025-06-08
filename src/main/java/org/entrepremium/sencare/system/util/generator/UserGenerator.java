package org.entrepremium.sencare.system.util.generator;

import org.entrepremium.sencare.feature.myuser.MyUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UserGenerator {
    
    private static final Random random = new Random();

    // Vietnamese first names
    private static final List<String> VIETNAMESE_FIRST_NAMES = Arrays.asList(
            "Minh", "Đức", "Hoa", "Linh", "Anh", "Thu", "Lan", "Mai", "Tuấn", "Hùng",
            "Phương", "Thảo", "Long", "Nam", "Hương", "Quỳnh", "Trinh", "Dũng", "Hải", "Sơn",
            "Yến", "Ngọc", "Hiền", "Trang", "Khánh", "Bình", "Thủy", "Giang", "Huy", "Khoa",
            "Vân", "Hoàng", "Thanh", "Quang", "Bảo", "Nhật", "Phúc", "Tiến", "Hiếu", "Tâm",
            "Lý", "Mỹ", "Chi", "Hà", "Kim", "Hồng", "Tuyết", "Xuân", "Châu", "Diễm"
    );

    // Vietnamese family names
    private static final List<String> VIETNAMESE_FAMILY_NAMES = Arrays.asList(
            "Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Phan", "Vũ", "Võ", "Đặng",
            "Bùi", "Đỗ", "Hồ", "Ngô", "Dương", "Lý", "Mai", "Đinh", "Lâm", "Trương",
            "Tạ", "Trịnh", "Cao", "Lương", "Tăng", "Mạc", "Quách", "Bạch", "Đoàn", "Lưu",
            "Tống", "Châu", "Chu", "Phùng", "Khổng", "Quân", "Vi", "La", "Phi", "Lục"
    );

    // Common Vietnamese middle names
    private static final List<String> VIETNAMESE_MIDDLE_NAMES = Arrays.asList(
            "Văn", "Thị", "Thanh", "Hồng", "Quốc", "Ngọc", "Đức", "Đình", "Minh", "Xuân",
            "Hữu", "Huy", "Tuấn", "Công", "Mạnh", "Duy", "Trung", "Hoài", "Bảo", "Kim"
    );

    // Email domains commonly used in Vietnam
    private static final List<String> EMAIL_DOMAINS = Arrays.asList(
        "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "fpt.edu.vn",
        "hcmut.edu.vn", "hust.edu.vn", "uit.edu.vn", "vnpost.vn", "vnu.edu.vn"
    );
    
    // User roles
    private static final List<String> USER_ROLES = Arrays.asList(
//        "admin",
        "user"
    );
    
    /**
     * Generate a single random MyUser
     */
    public static MyUser generateUser() {
        MyUser user = new MyUser();
        
        String fullName = generateVietnameseName();
        String email = generateEmail(fullName);
        String password = generatePassword();
        String roles = generateUserRoles();
        boolean enabled = generateEnabledStatus();
        
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setEnabled(enabled);
        user.setRoles(roles);
        
        return user;
    }

    /**
     * Generate multiple users
     */
    public static List<MyUser> generateSampleUsers(int count) {
        List<MyUser> users = new ArrayList<>();

        MyUser u1 = new MyUser();
        u1.setFullName("Hồ Trần Tiến");
        u1.setEmail("trantien100700@gmail.com");
        u1.setPassword("password123");
        u1.setEnabled(true);
        u1.setRoles("user");

        MyUser u2 = new MyUser();
        u2.setFullName("Nguyễn Tiến Đạt");
        u2.setEmail("datntse180123@fpt.edu.vn");
        u2.setPassword("password123");
        u2.setEnabled(true);
        u2.setRoles("user");

        MyUser u3 = new MyUser();
        u3.setFullName("Nguyễn Văn Đạt");
        u3.setEmail("datnguyen24.dev@gmail.com");
        u3.setPassword("password123");
        u3.setEnabled(true);
        u3.setRoles("user");

        users.add(u1);
        users.add(u2);
        users.add(u3);

        for (int i = 0; i < count - 3; i++) {
            users.add(generateUser());
        }
        return users;
    }
    
    /**
     * Generate a Vietnamese name
     */
    private static String generateVietnameseName() {
        String familyName = getRandomFromList(VIETNAMESE_FAMILY_NAMES);
        String middleName = getRandomFromList(VIETNAMESE_MIDDLE_NAMES);
        String firstName = getRandomFromList(VIETNAMESE_FIRST_NAMES);
        
        // Sometimes skip middle name (30% chance)
        if (random.nextDouble() < 0.3) {
            return familyName + " " + firstName;
        } else {
            return familyName + " " + middleName + " " + firstName;
        }
    }
    
    /**
     * Generate email based on Vietnamese name
     */
    private static String generateEmail(String fullName) {
        String[] nameParts = fullName.toLowerCase().split(" ");
        String domain = getRandomFromList(EMAIL_DOMAINS);
        
        StringBuilder emailPrefix = new StringBuilder();
        
        // Different email formats
        int format = random.nextInt(4);
        switch (format) {
            case 0: // firstname.lastname@domain
                if (nameParts.length >= 2) {
                    emailPrefix.append(removeVietnameseAccents(nameParts[nameParts.length - 1]))
                              .append(".")
                              .append(removeVietnameseAccents(nameParts[0]));
                }
                break;
            case 1: // firstnamelastname@domain
                if (nameParts.length >= 2) {
                    emailPrefix.append(removeVietnameseAccents(nameParts[nameParts.length - 1]))
                              .append(removeVietnameseAccents(nameParts[0]));
                }
                break;
            case 2: // firstname.lastname.number@domain
                if (nameParts.length >= 2) {
                    emailPrefix.append(removeVietnameseAccents(nameParts[nameParts.length - 1]))
                              .append(".")
                              .append(removeVietnameseAccents(nameParts[0]))
                              .append(random.nextInt(99) + 1);
                }
                break;
            case 3: // full name with dots
                for (int i = 0; i < nameParts.length; i++) {
                    emailPrefix.append(removeVietnameseAccents(nameParts[i]));
                    if (i < nameParts.length - 1) {
                        emailPrefix.append(".");
                    }
                }
                break;
        }
        
        return emailPrefix + "@" + domain;
    }
    
    /**
     * Remove Vietnamese accents for email generation
     */
    private static String removeVietnameseAccents(String text) {
        return text.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a")
                  .replaceAll("[èéẹẻẽêềếệểễ]", "e")
                  .replaceAll("[ìíịỉĩ]", "i")
                  .replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o")
                  .replaceAll("[ùúụủũưừứựửữ]", "u")
                  .replaceAll("[ỳýỵỷỹ]", "y")
                  .replaceAll("[đ]", "d")
                  .replaceAll("[ÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴ]", "A")
                  .replaceAll("[ÈÉẸẺẼÊỀẾỆỂỄ]", "E")
                  .replaceAll("[ÌÍỊỈĨ]", "I")
                  .replaceAll("[ÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠ]", "O")
                  .replaceAll("[ÙÚỤỦŨƯỪỨỰỬỮ]", "U")
                  .replaceAll("[ỲÝỴỶỸ]", "Y")
                  .replaceAll("[Đ]", "D");
    }
    
    /**
     * Generate a simple password (for testing purposes)
     */
    private static String generatePassword() {
//        String[] passwords = {
//            "password123", "123456789", "admin123", "user2024", "sencare123",
//            "vietnam123", "hospital123", "doctor123", "patient123", "system123"
//        };
//        return passwords[random.nextInt(passwords.length)];
        return "password123";
    }
    
    /**
     * Generate user roles (can have multiple roles)
     */
    private static String generateUserRoles() {
        // 80% single role, 20% multiple roles
        if (random.nextDouble() < 0.8) {
            return getRandomFromList(USER_ROLES);
        } else {
            // Multiple roles
            List<String> selectedRoles = new ArrayList<>();
            selectedRoles.add(getRandomFromList(USER_ROLES));
            
            // Add additional role
            String additionalRole = getRandomFromList(USER_ROLES);
            if (!selectedRoles.contains(additionalRole)) {
                selectedRoles.add(additionalRole);
            }
            
            return String.join(",", selectedRoles);
        }
    }
    
    /**
     * Generate enabled status (95% enabled)
     */
    private static boolean generateEnabledStatus() {
        return random.nextDouble() < 0.95;
    }
    
    /**
     * Get a random element from a list
     */
    private static String getRandomFromList(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }

    public static void main(String[] args) {
        // Example usage
        List<MyUser> users = generateSampleUsers(10);
        for (MyUser user : users) {
            System.out.println("Email: " + user.getEmail() + ", Password: " + user.getPassword() + ", Full Name: " + user.getFullName() +
                    ", Roles: " + user.getRoles() + ", Enabled: " + user.isEnabled());
        }
    }
}