import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<HashSet<String>> hashSets = new ArrayList<>();
        try {
            File text = new File("example_big.in");
            Scanner scnr = new Scanner(text);

            int T = scnr.nextInt();
            for (int i = 0; i < T; i++) {
                int N = scnr.nextInt();
                scnr.nextLine();
                for (int j = 0; j < N; j++) {
                    String line = scnr.nextLine();
                    String[] parts = line.split(" ");
                    String word1 = parts[0].toLowerCase();
                    String word2 = parts[1].toLowerCase();

                    if (hashSets.isEmpty()) {
                        hashSets.add(new HashSet<String>());
                        hashSets.get(0).add(word1);
                        hashSets.get(0).add(word2);
                    } else {
                        boolean found = false;
                        for (int k = 0; k < hashSets.size(); k++) {
                            if (hashSets.get(k).contains(word1)) {
                                hashSets.get(k).add(word2);
                                found = true;
                            }
                            if (hashSets.get(k).contains(word2)) {
                                hashSets.get(k).add(word1);
                                found = true;
                            }
                        }
                        if (!found) {
                            HashSet<String> temp = new HashSet<>();
                            temp.add(word1);
                            temp.add(word2);
                            hashSets.add(temp);
                        }
                    }
                }

                boolean change = true;
                while (change) {
                    change = false;
                    for (int j = 0; j < hashSets.size() - 1; j++) {
                        for (int k = 0; k < hashSets.size(); k++) {
                            if (j != k){
                                if (hashSets.get(j).stream().anyMatch(hashSets.get(k)::contains)) {
                                    hashSets.get(j).addAll(hashSets.get(k));
                                    hashSets.remove(k);
                                    change = true;
                                }
                            }
                        }
                    }
                }


                int Q = scnr.nextInt();
                scnr.nextLine();
                for (int j = 0; j < Q; j++) {

                    String line = scnr.nextLine();
                    String[] parts = line.split(" ");
                    String word1 = parts[0].toLowerCase();
                    String word2 = parts[1].toLowerCase();

                    boolean found = false;
                    for (int k = 0; k < hashSets.size(); k++) {
                        if (word1.equals(word2)) {
                            System.out.println("synonyms");
                            found = true;
                            break;
                        } else if (hashSets.get(k).contains(word1) && hashSets.get(k).contains(word2)) {
                            System.out.println("synonyms");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("different");
                    }
                }
                hashSets.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
