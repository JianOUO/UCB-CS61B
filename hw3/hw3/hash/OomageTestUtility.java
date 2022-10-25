package hw3.hash;

import java.sql.Array;
import java.util.*;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int bucketNum;
        int totalNum = oomages.size();
        double min = totalNum / 50.0;
        double max = totalNum / 2.5;
        List<Oomage>[] list = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            list[i] = new ArrayList<>();
        }
        for (Oomage O : oomages) {
            bucketNum = (O.hashCode() & 0x7FFFFFFF) % M;
            list[bucketNum].add(O);
        }
        for (List<Oomage> templist : list) {
            if (templist.size() > max || templist.size() < min) {
                return false;
            }
        }
        return true;
    }
}
