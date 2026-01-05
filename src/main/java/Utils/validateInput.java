package Utils;

public class validateInput {

    public static boolean isValidGhanaCard(String ghanaCardNumber) {
        return ghanaCardNumber != null && ghanaCardNumber.startsWith("GHA")&& ghanaCardNumber.length() >= 10;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Basic validation for a typical 10-digit phone number
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

    public static boolean isValidPin(String pin) {
        // PIN must be exactly 4 digits
        return pin != null && pin.matches("\\d{4}");
    }

    public static boolean isValidName(String name) {
        // Name should not be null or empty and can contain letters, spaces, and some punctuation
        return name != null
                && !name.trim().isEmpty()
                && name.matches("[a-zA-Z\\s.'-]+")
                && name.length()>=4;
    }

    public static boolean isValidPassword(String password){
        return password != null
                && password.length() >= 8
                && password.matches(".*[A-Z].*")           // At least one uppercase
                && password.matches(".*[a-z].*")           // At least one lowercase
                && password.matches(".*\\d.*")             // At least one digit
                && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*"); // At least one symbol
    }


}
