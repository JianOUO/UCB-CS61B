import java.util.*;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import com.sun.source.tree.Tree;
import edu.princeton.cs.introcs.In;

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";
    private static TreeSet<String> answer;
    private static Trie trie;
    private static int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    private static int[] dx = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
    private static class CompareByLen implements Comparator<String> {
        public int compare(String s1, String s2) {
            int diff = s2.length() - s1.length();
            return diff == 0 ? s1.compareTo(s2) : diff;
        }
    }
    private static char[][] board;
    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        // YOUR CODE HERE
        if (k <= 0 || !isBoardRect(boardFilePath) || dictPath == null) {
            throw new IllegalArgumentException();
        }
        In in = new In(dictPath);
        trie = new Trie();
        while (in.hasNextLine()) {
            trie.put(in.readLine());
        }
        answer = new TreeSet<>(new CompareByLen());
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int x = 0; x < board.length; x += 1) {
            for (int y = 0; y < board[0].length; y += 1) {
                String s = "";
                dfs(x, y, visited, s);
            }
        }
        List<String> l = new ArrayList<>();
        while (l.size() < k) {
            if (answer.isEmpty()) {
                break;
            }
            l.add(answer.pollFirst());
        }
        return l;
    }

    private static void dfs (int x, int y, boolean[][] visited, String s) {
        if (!inBound(x, y) || visited[x][y]) {
            return;
        }
        s += board[x][y];
        if (!trie.startwith(s)) {
            return;
        }
        visited[x][y] = true;
        if (s.length() > 2 && trie.search(s)) {
            answer.add(s);
        }
        for (int i = 0; i < 8; i += 1) {
            int nextx = x + dx[i];
            int nexty = y + dy[i];
            dfs(nextx, nexty, visited, s);
        }
        visited[x][y] = false;
    }

    private static boolean inBound(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    private static boolean isBoardRect(String boardFilePath) {
        if (boardFilePath == null) {
            return false;
        }
        In in = new In(boardFilePath);
        String[] input = in.readAllLines();
        int row = input.length;
        int colum = input[0].length();
        board = new char[row][colum];
        for (int x = 0; x < row; x += 1) {
            if (input[x].length() != colum) {
                return false;
            }
            for (int y = 0; y < input[x].length(); y += 1) {
                board[x][y] = input[x].charAt(y);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (String s : solve(7, "exampleBoard.txt")) {
            System.out.println(s);
        }
    }
}
