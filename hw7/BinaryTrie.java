import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class BinaryTrie implements Serializable {
    private static int R = 256;
    private Node root;
    private static class Node implements Comparable<Node>, Serializable {
        private final char ch;
        private final int freq;
        private final Node left, right;
        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }

        public boolean isLeaf() {
            return (left == null && right == null);
        }
    }
    public BinaryTrie(Map<Character, Integer> frequencyTable){
        root = buildTries(frequencyTable);
    }

    private Node buildTries(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<>();
         for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
             if (entry.getValue() != 0) {
                 pq.insert(new Node(entry.getKey(), entry.getValue(), null, null));
             }
         }
         while (pq.size() > 1) {
             Node left = pq.delMin();
             Node right = pq.delMin();
             Node parent = new Node('\0', left.freq + right.freq, left, right);
             pq.insert(parent);
         }
         return pq.delMin();
    }
    public Match longestPrefixMatch(BitSequence querySequence) {
        return findMatch(root, querySequence, 0);
        /**Node n = root;
        int i = 0;
        while (true) {
            if (!n.isLeaf()) {
                if (querySequence.bitAt(i) == 0) {
                    n = n.left;
                    i += 1;
                } else {
                    n = n.right;
                    i += 1;
                }
            } else {
                break;
            }

        }

        BitSequence bit = new BitSequence(querySequence.firstNBits(i));
        return new Match(bit, n.ch);
         */
    }

    private Match findMatch(Node n, BitSequence b, int d) {
        if (!n.isLeaf()) {
            int bit = b.bitAt(d);
            if (bit == 0) {
                return findMatch(n.left, b, d + 1);
            } else {
                return findMatch(n.right, b, d + 1);
            }
        } else {
            BitSequence bit = new BitSequence(b.firstNBits(d));
            return new Match(bit, n.ch);
        }
    }

    public Map<Character, BitSequence> buildLookupTable() {
        Map<Character, BitSequence> lookup = new TreeMap<>();
        buildCode(lookup, root, "");
        return lookup;
    }

    private void buildCode(Map<Character, BitSequence> map, Node n, String s) {
        if (!n.isLeaf()) {
            buildCode(map, n.left, s + "0");
            buildCode(map, n.right, s + "1");
        } else {
            map.put(n.ch, new BitSequence(s));
        }
    }

}
