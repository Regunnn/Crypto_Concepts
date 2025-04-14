import java.util.Scanner;

public class HillCipher {

    private static int charToInt(char c) {
        return c - 'A';
    }

    private static char intToChar(int i) {
        return (char) (i + 'A');
    }

    private static int[] multiplyMatrix(int[][] key, int[] vector) {
        int size = key.length;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = 0;
            for (int j = 0; j < size; j++) {
                result[i] += key[i][j] * vector[j];
            }
            result[i] %= 26;
        }
        return result;
    }

    private static int modInverse(int a) {
        a = a % 26;
        for (int x = 1; x < 26; x++) {
            if ((a * x) % 26 == 1)
                return x;
        }
        throw new IllegalArgumentException("No modular inverse for " + a);
    }

    private static int[][] inverseKeyMatrix2x2(int[][] key) {
        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % 26;
        det = (det + 26) % 26;
        int detInv = modInverse(det);

        int[][] inv = new int[2][2];
        inv[0][0] = key[1][1];
        inv[0][1] = -key[0][1];
        inv[1][0] = -key[1][0];
        inv[1][1] = key[0][0];

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                inv[i][j] = ((inv[i][j] * detInv) % 26 + 26) % 26;

        return inv;
    }

    private static int[][] inverseKeyMatrix3x3(int[][] key) {
        int[][] inv = new int[3][3];
        int det = 0;

        for (int i = 0; i < 3; i++) {
            det += key[0][i] * (key[1][(i + 1) % 3] * key[2][(i + 2) % 3] - key[1][(i + 2) % 3] * key[2][(i + 1) % 3]);
        }

        det = ((det % 26) + 26) % 26;
        int detInv = modInverse(det);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int val = key[(j + 1) % 3][(i + 1) % 3] * key[(j + 2) % 3][(i + 2) % 3]
                        - key[(j + 1) % 3][(i + 2) % 3] * key[(j + 2) % 3][(i + 1) % 3];
                inv[i][j] = ((val * detInv) % 26 + 26) % 26;
            }
        }

        return inv;
    }

    public static String encrypt(String plaintext, int[][] key) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        int size = key.length;

        while (plaintext.length() % size != 0) {
            plaintext += "X";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += size) {
            int[] vector = new int[size];
            for (int j = 0; j < size; j++) {
                vector[j] = charToInt(plaintext.charAt(i + j));
            }

            int[] enc = multiplyMatrix(key, vector);
            for (int val : enc) {
                result.append(intToChar(val));
            }
        }

        return result.toString();
    }

    public static String decrypt(String ciphertext, int[][] key) {
        int size = key.length;
        int[][] invKey = size == 2 ? inverseKeyMatrix2x2(key) : inverseKeyMatrix3x3(key);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += size) {
            int[] vector = new int[size];
            for (int j = 0; j < size; j++) {
                vector[j] = charToInt(ciphertext.charAt(i + j));
            }

            int[] dec = multiplyMatrix(invKey, vector);
            for (int val : dec) {
                result.append(intToChar(val));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of key matrix (2 or 3): ");
        int size = sc.nextInt();

        if (size != 2 && size != 3) {
            System.out.println("Only 2x2 or 3x3 key matrices are supported.");
            return;
        }

        int[][] key = new int[size][size];
        System.out.println("Enter the key matrix values (row-wise):");
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                key[i][j] = sc.nextInt();

        sc.nextLine(); 

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nEncrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}
