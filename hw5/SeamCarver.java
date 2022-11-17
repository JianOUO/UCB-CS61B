import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private Picture picture;
    public SeamCarver(Picture picture) {
        this.picture = picture;
    }
    public Picture picture() {
        return picture;
    }                      // current picture
    public int width() {
        return picture.width();
    }                       // width of current picture
    public int height() {
        return picture.height();
    }                        // height of current picture
    public double energy(int x, int y) {
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        Color left, right, up, down;
        if (x == 0) {
            left = picture.get(width() - 1, y);
            if (width() > 1) {
                right = picture.get(x + 1, y);
            } else {
                right = left;
            }
        } else if (x == width() - 1) {
            right = picture.get(0, y);
            left = picture.get(x - 1, y);
        } else {
            left = picture.get(x - 1, y);
            right = picture.get(x + 1, y);
        }
        if (y == 0) {
            up = picture.get(x, height() - 1);
            if (height() > 1) {
                down = picture.get(x, y + 1);
            } else {
                down = up;
            }
        } else if (y == height() - 1) {
            down = picture.get(x, 0);
            up = picture.get(x, y - 1);
        } else {
            down = picture.get(x, y + 1);
            up = picture.get(x, y - 1);
        }
        double deltaX = Math.pow(left.getBlue() - right.getBlue(), 2)
                + Math.pow(left.getGreen() - right.getGreen(), 2)
                + Math.pow(left.getRed() - right.getRed(), 2);
        double deltaY = Math.pow(up.getBlue() - down.getBlue(), 2)
                + Math.pow(up.getGreen() - down.getGreen(), 2)
                + Math.pow(up.getRed() - down.getRed(), 2);
        return deltaX + deltaY;
    }           // energy of pixel at column x and row y
    public int[] findVerticalSeam() {
        int[] seam = new int[height()];
        double[] M = new double[width()];
        double[] temp = new double[width()];
        int[][] path = new int[width()][height()];
        for (int j = 0; j < height(); j++) {
            for (int i = 0; i < width(); i++) {
                double min;
                if (i == 0) {
                    if (width() > 1) {
                        if (M[i] < M[i + 1]) {
                            min = M[i];
                            path[i][j] = i;
                        } else {
                            min = M[i + 1];
                            path[i][j] = i + 1;
                        }
                    } else {
                        min = M[i];
                        path[i][j] = i;
                    }
                } else if (i == width() - 1) {
                    if (M[i] < M[i - 1]) {
                        min = M[i];
                        path[i][j] = i;
                    } else {
                        min = M[i - 1];
                        path[i][j] = i - 1;
                    }
                } else {
                    if (M[i - 1] < M[i]) {
                        if (M[i - 1] < M[i + 1]) {
                            min = M[i - 1];
                            path[i][j] = i - 1;
                        } else {
                            min = M[i + 1];
                            path[i][j] = i + 1;
                        }
                    }  else {
                        if (M[i] < M[i + 1]) {
                            min = M[i];
                            path[i][j] = i;
                        } else {
                            min = M[i + 1];
                            path[i][j] = i + 1;
                        }
                    }
                }
                temp[i] = energy(i, j) + min;
            }
            System.arraycopy(temp, 0, M, 0, width());
        }
        double min = Double.MAX_VALUE;
        int col = 0;
        for (int i = 0; i < width(); i++) {
            if (M[i] < min) {
                min = M[i];
                col = i;
            }
        }
        for (int i = height() - 1; i >= 0; i--) {
            seam[i] = col;
            col = path[col][i];
        }
        return seam;
    }          // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        Picture picture1 = new Picture(height(), width());
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                picture1.set(i, j, picture.get(j, i));
            }
        }
        SeamCarver sc = new SeamCarver(picture1);
        return sc.findVerticalSeam();
    }             // sequence of indices for vertical seam
    public void removeHorizontalSeam(int[] seam) {
        picture = new Picture(SeamRemover.removeHorizontalSeam(picture, seam));
    }  // remove horizontal seam from picture
    public void removeVerticalSeam(int[] seam) {
        picture = new Picture(SeamRemover.removeVerticalSeam(picture, seam));
    }    // remove vertical seam from picture


}
