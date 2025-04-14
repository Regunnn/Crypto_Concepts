import java.util.Scanner;

public class AutokeyCipher {

    public static String encrypt(String plaintext, String keyword) {
        StringBuilder keyBuilder = new StringBuilder(keyword);

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                keyBuilder.append(ch);
            }
        }

        StringBuilder result = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            if (Character.isLetter(plainChar)) {
                char keyChar = keyBuilder.charAt(keyIndex);
                keyIndex++;

                int shift = Character.toLowerCase(keyChar) - 'a';

                if (Character.isUpperCase(plainChar)) {
                    char enc = (char) ((plainChar - 'A' + shift) % 26 + 'A');
                    result.append(enc);
                } else {
                    char enc = (char) ((plainChar - 'a' + shift) % 26 + 'a');
                    result.append(enc);
                }
            } else {
                result.append(plainChar);
            }
        }

        return result.toString();
    }

   
    public static String decrypt(String ciphertext, String keyword) {
        StringBuilder result = new StringBuilder();
        StringBuilder keyBuilder = new StringBuilder(keyword);
        int keyIndex = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            if (Character.isLetter(cipherChar)) {
                char keyChar = keyBuilder.charAt(keyIndex);
                keyIndex++;

                int shift = Character.toLowerCase(keyChar) - 'a';
                char plainChar;

                if (Character.isUpperCase(cipherChar)) {
                    plainChar = (char) ((cipherChar - 'A' - shift + 26) % 26 + 'A');
                } else {
                    plainChar = (char) ((cipherChar - 'a' - shift + 26) % 26 + 'a');
                }

                result.append(plainChar);
                keyBuilder.append(plainChar); 
            } else {
                result.append(cipherChar);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        String encrypted = encrypt(plaintext, keyword);
        String decrypted = decrypt(encrypted, keyword);

        System.out.println("\nEncrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }
}
