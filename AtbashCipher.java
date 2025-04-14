import java.util.Scanner;

public class AtbashCipher {

    
    public static String atbash(String text) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char ch = (char) ('Z' - (character - 'A'));
                result.append(ch);
            } else if (Character.isLowerCase(character)) {
                char ch = (char) ('z' - (character - 'a'));
                result.append(ch);
            } else {
                result.append(character); 
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String original = scanner.nextLine();

        String encrypted = atbash(original);
        String decrypted = atbash(encrypted); 

        System.out.println("\nOriginal:  " + original);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }
}
