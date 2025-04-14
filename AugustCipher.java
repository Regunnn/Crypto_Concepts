import java.util.Scanner;

public class AugustCipher {

    
    public static String encrypt(String text) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char ch = (char)(((character - 'A' + 1) % 26) + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(character)) {
                char ch = (char)(((character - 'a' + 1) % 26) + 'a');
                result.append(ch);
            } else {
                result.append(character); 
            }
        }

        return result.toString();
    }

    
    public static String decrypt(String text) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char ch = (char)(((character - 'A' - 1 + 26) % 26) + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(character)) {
                char ch = (char)(((character - 'a' - 1 + 26) % 26) + 'a');
                result.append(ch);
            } else {
                result.append(character); 
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String input = scanner.nextLine();

        String encrypted = encrypt(input);
        String decrypted = decrypt(encrypted);

        System.out.println("\nEncrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }
}
