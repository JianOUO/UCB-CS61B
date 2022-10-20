package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF setforfull, setforper;
    private boolean[][] array;
    private int numberofopen, top, bottom;
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N can't <= 0");
        }
        numberofopen = 0;
        array = new boolean[N][N];
        setforfull = new WeightedQuickUnionUF(N * N + 1);
        setforper = new WeightedQuickUnionUF(N * N + 2);
        top = N * N;
        bottom = N * N + 1;
        for (boolean[] i : array) {
            for (boolean j : i) {
                j = false;
            }
        }
    }
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= array.length || col >= array.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        array[row][col] = true;
        numberofopen += 1;
        if (row == 0) {
            setforper.union(top, xyToNumber(row, col));
            setforfull.union(top, xyToNumber(row, col));
        }
        if (row == array.length - 1) {
            setforper.union(bottom, xyToNumber(row, col));
        }
        if (row > 0 && isOpen(row - 1, col)) {
            setforper.union(xyToNumber(row, col), xyToNumber(row - 1, col));
            setforfull.union(xyToNumber(row, col), xyToNumber(row - 1, col));
        }
        if (row < array.length - 1 && isOpen(row + 1, col)) {
            setforper.union(xyToNumber(row, col), xyToNumber(row + 1, col));
            setforfull.union(xyToNumber(row, col), xyToNumber(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            setforper.union(xyToNumber(row, col), xyToNumber(row, col - 1));
            setforfull.union(xyToNumber(row, col), xyToNumber(row, col - 1));
        }
        if (col < array.length - 1 && isOpen(row, col + 1)) {
            setforper.union(xyToNumber(row, col), xyToNumber(row, col + 1));
            setforfull.union(xyToNumber(row, col), xyToNumber(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= array.length || col >= array.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return array[row][col];
    }
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= array.length || col >= array.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return setforfull.connected(xyToNumber(row, col), top);
    }

    private int xyToNumber(int row, int col) {
        return row * array.length + col;
    }
    public int numberOfOpenSites() {
        return numberofopen;
    }

    public boolean percolates() {
        return setforper.connected(top, bottom);
    }

}
