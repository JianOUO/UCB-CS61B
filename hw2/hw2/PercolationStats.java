package hw2;

public class PercolationStats {

    private double[] x;
    private Percolation set;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        x = new double[T];
        for (int i = 0; i < T; i++) {
            set = pf.make(N);
            while (!set.percolates()) {
                int randomx = edu.princeton.cs.introcs.StdRandom.uniform(0, N);
                int randomy = edu.princeton.cs.introcs.StdRandom.uniform(0, N);
                set.open(randomx, randomy);
            }
            x[i] = set.numberOfOpenSites() / Double.valueOf(N * N);
        }
        /*for (int i : x) {
            System.out.println(i);
        }
         */
    }

    public double mean() {
        return edu.princeton.cs.introcs.StdStats.mean(x);
    }

    public double stddev() {
        return edu.princeton.cs.introcs.StdStats.stddev(x);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(x.length);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(x.length);
    }

}

    //*/