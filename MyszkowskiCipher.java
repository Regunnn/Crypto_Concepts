import java.util.*;

public class MyszkowskiCipher {

    public static int[] getKeyPattern(String keyword) {
        keyword = keyword.toUpperCase();
        int len = keyword.length();
        int[] keyPattern = new int[len];

        Character[] letters = new Character[len];
        for (int i = 0; i < len; i++) {
            letters[i] = keyword.charAt(i);
        }

        Character[] sorted = letters.clone();
        Arrays.sort(sorted);

        Map<Character, Integer> orderMap = new LinkedHashMap<>();
        int number = 1;
        for (Character c : sorted) {
            if (!orderMap.containsKey(c)) {
                orderMap.put(c, number++);
            }
        }

        for (int i = 0; i < len; i++) {
            keyPattern[i] = orderMap.get(letters[i]);
        }

        return keyPattern;
    }

    public static String encrypt(String plaintext, String keyword) {
        plaintext = plaintext.replaceAll("[^A-Za-z]", "").toUpperCase();
        int[] key = getKeyPattern(keyword);
        int cols = key.length;
        int rows = (int) Math.ceil((double) plaintext.length() / cols);

        char[][] matrix = new char[rows][cols];
        int index = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (index < plaintext.length()) {
                    matrix[r][c] = plaintext.charAt(index++);
                } else {
                    matrix[r][c] = 'X'; 
                }
            }
        }

        List<int[]> columnPairs = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            columnPairs.add(new int[]{key[i], i});
        }

        columnPairs.sort(Comparator.comparingInt(pair -> pair[0]));

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < columnPairs.size(); ) {
            int currKey = columnPairs.get(i)[0];
            List<Integer> groupCols = new ArrayList<>();

            while (i < columnPairs.size() && columnPairs.get(i)[0] == currKey) {
                groupCols.add(columnPairs.get(i)[1]);
                i++;
            }

            for (int col : groupCols) {
                for (int r = 0; r < rows; r++) {
                    ciphertext.append(matrix[r][col]);
                }
            }
        }

        return ciphertext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = sc.nextLine();

        System.out.print("Enter the keyword: ");
        String keyword = sc.nextLine();

        String encrypted = encrypt(plaintext, keyword);

        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {
            formatted.append(encrypted.charAt(i));
            if ((i + 1) % 5 == 0) {
                formatted.append(" ");
            }
        }

        System.out.println("\nEncrypted Text:\n" + formatted.toString().trim());
        sc.close();
    }
}
