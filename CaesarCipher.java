import java.util.Scanner;

public class CaesarCipher {

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char ch = (char)(((character - 'A' + shift) % 26) + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(character)) {
                char ch = (char)(((character - 'a' + shift) % 26) + 'a');
                result.append(ch);
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String original = scanner.nextLine();

        System.out.print("Enter the shift value: ");
        int shift = scanner.nextInt();

        String encrypted = encrypt(original, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("\nOriginal:  " + original);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }
}
