import java.util.Scanner;

public class GronsfeldCipher {

    public static String generateKey(String text, String key) {
        StringBuilder newKey = new StringBuilder();
        int keyLen = key.length();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                newKey.append(key.charAt(j % keyLen));
                j++;
            } else {
                newKey.append('0');
            }
        }

        return newKey.toString();
    }

    
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = generateKey(text, key);

        for (int i = 0; i < text.length(); i++) {
            char plainChar = text.charAt(i);
            int shift = key.charAt(i) - '0';

            if (Character.isUpperCase(plainChar)) {
                char ch = (char)((plainChar - 'A' + shift) % 26 + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(plainChar)) {
                char ch = (char)((plainChar - 'a' + shift) % 26 + 'a');
                result.append(ch);
            } else {
                result.append(plainChar);
            }
        }

        return result.toString();
    }

    
    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = generateKey(text, key);

        for (int i = 0; i < text.length(); i++) {
            char cipherChar = text.charAt(i);
            int shift = key.charAt(i) - '0';

            if (Character.isUpperCase(cipherChar)) {
                char ch = (char)((cipherChar - 'A' - shift + 26) % 26 + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(cipherChar)) {
                char ch = (char)((cipherChar - 'a' - shift + 26) % 26 + 'a');
                result.append(ch);
            } else {
                result.append(cipherChar);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String input = scanner.nextLine();

        System.out.print("Enter numeric key (digits only): ");
        String key = scanner.nextLine();

        if (!key.matches("\\d+")) {
            System.out.println("Invalid key. Only digits are allowed.");
        } else {
            String encrypted = encrypt(input, key);
            String decrypted = decrypt(encrypted, key);

            System.out.println("\nEncrypted: " + encrypted);
            System.out.println("Decrypted: " + decrypted);
        }

        scanner.close();
    }
}
