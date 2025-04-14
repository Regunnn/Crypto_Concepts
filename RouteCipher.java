import java.util.Scanner;

public class RouteCipher {

    public static String encrypt(String plaintext, int columns) {
        plaintext = plaintext.replaceAll("[^A-Za-z]", "").toUpperCase();
        int length = plaintext.length();
        int rows = (int) Math.ceil((double) length / columns);

        char[][] grid = new char[rows][columns];
        int idx = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (idx < length) {
                    grid[r][c] = plaintext.charAt(idx++);
                } else {
                    grid[r][c] = 'X'; 
                }
            }
        }

       
        StringBuilder cipher = new StringBuilder();
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                cipher.append(grid[r][c]);
            }
        }

        return cipher.toString();
    }

  
    public static String decrypt(String cipher, int columns) {
        int length = cipher.length();
        int rows = (int) Math.ceil((double) length / columns);

        char[][] grid = new char[rows][columns];
        int idx = 0;

       
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                if (idx < length) {
                    grid[r][c] = cipher.charAt(idx++);
                }
            }
        }

     
        StringBuilder plaintext = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                plaintext.append(grid[r][c]);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String message = sc.nextLine();

        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        String encrypted = encrypt(message, cols);
        String decrypted = decrypt(encrypted, cols);

        System.out.println("\nEncrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}
