package lab11.graphs;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private Maze maze;
    private boolean hascycle;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        edgeTo[0] = 0;
        distTo[0] = 0;
    }

    private void dfs(int v) {
        marked[v] = true;
        announce();
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                distTo[w] = distTo[v] + 1;
                marked[w] = true;
                announce();
                edgeTo[w] = v;
                announce();
                dfs(w);
                if (hascycle) {
                    return;
                }
            } else if (w != edgeTo[v]) {
                edgeTo[w] = v;
                announce();
                hascycle = true;
                return;
            }
        }
    }



    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(0);
    }

    // Helper methods go here
}

