import java.util.HashMap;

public class Trie {
    private class Node {
        private HashMap<Character, Node> child;
        boolean isword;
        private Node() {
            child = new HashMap<>();
            isword = false;
        }
    }

    private Node root;

    public Trie() {
        root = new Node();
    }
    public void put(String s) {
       Node n = root;
        for (char c : s.toCharArray()) {
            n.child.putIfAbsent(c, new Node());
            n = n.child.get(c);
        }
        n.isword = true;
    }


    public boolean search(String s) {
        Node n = root;
        for (char c : s.toCharArray()) {
            n = n.child.get(c);
            if (n == null) {
                return false;
            }
        }
        return n.isword;
    }

    public boolean startwith(String s) {
        Node n = root;
        for (char c : s.toCharArray()) {
            n = n.child.get(c);
            if (n == null) {
                return false;
            }
        }
        return true;
    }
}
