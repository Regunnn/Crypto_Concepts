import java.util.Scanner;

public class BeaufortCipher {

    public static String generateKey(String text, String key) {
        StringBuilder newKey = new StringBuilder();
        int keyLen = key.length();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                newKey.append(key.charAt(j % keyLen));
                j++;
            } else {
                newKey.append(' '); 
            }
        }

        return newKey.toString();
    }

    public static String beaufort(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = generateKey(text, key);

        for (int i = 0; i < text.length(); i++) {
            char plainChar = text.charAt(i);
            char keyChar = key.charAt(i);

            if (Character.isUpperCase(plainChar)) {
                char ch = (char)(((Character.toUpperCase(keyChar) - plainChar + 26) % 26) + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(plainChar)) {
                char ch = (char)(((Character.toLowerCase(keyChar) - plainChar + 26) % 26) + 'a');
                result.append(ch);
            } else {
                result.append(plainChar);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String input = scanner.nextLine();

        System.out.print("Enter keyword: ");
        String key = scanner.nextLine();

        String encrypted = beaufort(input, key);
        String decrypted = beaufort(encrypted, key); 

        System.out.println("\nEncrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }
}
