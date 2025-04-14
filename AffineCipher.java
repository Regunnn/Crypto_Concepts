import java.util.Scanner;

public class AffineCipher {

    public static char encryptChar(char ch, int a, int b) {
        if (Character.isUpperCase(ch)) {
            return (char)(((a * (ch - 'A') + b) % 26) + 'A');
        } else if (Character.isLowerCase(ch)) {
            return (char)(((a * (ch - 'a') + b) % 26) + 'a');
        } else {
            return ch;
        }
    }


    public static char decryptChar(char ch, int a, int b) {
        int a_inv = modInverse(a, 26);
        if (a_inv == -1) {
            throw new IllegalArgumentException("Multiplicative inverse doesn't exist for a = " + a);
        }

        if (Character.isUpperCase(ch)) {
            return (char)((((a_inv * ((ch - 'A') - b + 26)) % 26) + 'A'));
        } else if (Character.isLowerCase(ch)) {
            return (char)((((a_inv * ((ch - 'a') - b + 26)) % 26) + 'a'));
        } else {
            return ch;
        }
    }

    
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return -1;
    }

    public static String encrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            result.append(encryptChar(ch, a, b));
        }
        return result.toString();
    }

    public static String decrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            result.append(decryptChar(ch, a, b));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String input = scanner.nextLine();

        System.out.print("Enter key 'a' (must be coprime with 26): ");
        int a = scanner.nextInt();

        System.out.print("Enter key 'b': ");
        int b = scanner.nextInt();

        if (modInverse(a, 26) == -1) {
            System.out.println("Invalid key 'a'. It must be coprime with 26.");
        } else {
            String encrypted = encrypt(input, a, b);
            String decrypted = decrypt(encrypted, a, b);

            System.out.println("\nEncrypted: " + encrypted);
            System.out.println("Decrypted: " + decrypted);
        }

        scanner.close();
    }
}
