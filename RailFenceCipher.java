import java.util.Scanner;

public class RailFenceCipher {

    public static String encrypt(String text, int rails) {
        if (rails <= 1) return text;

        text = text.replaceAll("[^A-Za-z]", "").toUpperCase();
        StringBuilder[] rail = new StringBuilder[rails];

        for (int i = 0; i < rails; i++) {
            rail[i] = new StringBuilder();
        }

        int dir = 1;
        int row = 0;

        for (char ch : text.toCharArray()) {
            rail[row].append(ch);
            row += dir;

            if (row == rails - 1 || row == 0) {
                dir *= -1;
            }
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder r : rail) {
            result.append(r);
        }

        return result.toString();
    }


    public static String decrypt(String cipher, int rails) {
        if (rails <= 1) return cipher;

        int len = cipher.length();
        boolean[][] marker = new boolean[rails][len];

    
        int dir = 1, row = 0;
        for (int col = 0; col < len; col++) {
            marker[row][col] = true;
            row += dir;
            if (row == rails - 1 || row == 0) dir *= -1;
        }

        char[][] rail = new char[rails][len];
        int idx = 0;

        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < len; j++) {
                if (marker[i][j] && idx < len) {
                    rail[i][j] = cipher.charAt(idx++);
                }
            }
        }

        
        StringBuilder result = new StringBuilder();
        row = 0;
        dir = 1;
        for (int col = 0; col < len; col++) {
            result.append(rail[row][col]);
            row += dir;
            if (row == rails - 1 || row == 0) dir *= -1;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String input = sc.nextLine();

        System.out.print("Enter number of rails: ");
        int rails = sc.nextInt();
        sc.nextLine(); 

        String encrypted = encrypt(input, rails);
        String decrypted = decrypt(encrypted, rails);

        System.out.println("\nEncrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}
