import java.util.*;

public class N_gram {

    public static List<String> generateNGrams(String text, int n) {
        List<String> ngrams = new ArrayList<>();
        text = text.replaceAll("[^A-Za-z]", "").toUpperCase();  // Clean and normalize

        for (int i = 0; i <= text.length() - n; i++) {
            String gram = text.substring(i, i + n);
            ngrams.add(gram);
        }

        return ngrams;
    }

    public static Map<String, Integer> countFrequencies(List<String> ngrams) {
        Map<String, Integer> freqMap = new HashMap<>();

        for (String gram : ngrams) {
            freqMap.put(gram, freqMap.getOrDefault(gram, 0) + 1);
        }

        return freqMap;
    }

    public static void displayTopNGrams(Map<String, Integer> freqMap, int topN) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freqMap.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\nTop " + topN + " most frequent n-grams:");
        for (int i = 0; i < Math.min(topN, list.size()); i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String input = sc.nextLine();

        System.out.print("Enter n for N-gram (e.g., 1 for unigram, 2 for bigram, 3 for trigram): ");
        int n = sc.nextInt();

        System.out.print("Enter how many top frequent n-grams to display: ");
        int topN = sc.nextInt();

        List<String> ngrams = generateNGrams(input, n);
        Map<String, Integer> frequencies = countFrequencies(ngrams);

        displayTopNGrams(frequencies, topN);

        sc.close();
    }
}
