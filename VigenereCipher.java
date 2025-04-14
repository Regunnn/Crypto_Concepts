import java.util.Scanner;

public class VigenereCipher {

    public static String generateKey(String text, String key) {
        StringBuilder newKey = new StringBuilder();
        int keyLen = key.length();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                newKey.append(key.charAt(j % keyLen));
                j++;
            } else {
                newKey.append(ch);
            }
        }

        return newKey.toString();
    }

 
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = generateKey(text, key);

        for (int i = 0; i < text.length(); i++) {
            char plainChar = text.charAt(i);
            char keyChar = key.charAt(i);

            if (Character.isUpperCase(plainChar)) {
                char ch = (char)((plainChar + Character.toUpperCase(keyChar) - 2 * 'A') % 26 + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(plainChar)) {
                char ch = (char)((plainChar + Character.toLowerCase(keyChar) - 2 * 'a') % 26 + 'a');
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
            char keyChar = key.charAt(i);

            if (Character.isUpperCase(cipherChar)) {
                char ch = (char)((cipherChar - Character.toUpperCase(keyChar) + 26) % 26 + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(cipherChar)) {
                char ch = (char)((cipherChar - Character.toLowerCase(keyChar) + 26) % 26 + 'a');
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

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        String encrypted = encrypt(input, keyword);
        String decrypted = decrypt(encrypted, keyword);

        System.out.println("\nEncrypted (Vigenère Cipher): " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }
}
