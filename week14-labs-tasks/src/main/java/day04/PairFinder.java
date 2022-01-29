package day04;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class PairFinder {

    public int findPairs(int[] array) {

        Map<Integer, Integer> pairs = new TreeMap<>();

        for (Integer actual : array) {
            pairs.compute(actual, (k, v) -> (v == null) ? 1 : v + 1);
        }
        System.out.println(pairs);
        return pairs.values().stream().mapToInt(i->i/2).sum();

    }

    public int findPairs2(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);

        Arrays.sort(result);

        int count =0;
        for (int i = 1; i < result.length; i++) {
            if(result[i] == result[i-1]){
                count++;
                i++;
            }
        }
        return count;
    }


    public static void main(String[] args) {

        int[] array = {1,3,3,5,3,3,5,8,5,8};
        PairFinder pairFinder = new PairFinder();
        System.out.println(pairFinder.findPairs(array));
        System.out.println(pairFinder.findPairs2(array));
    }

}
