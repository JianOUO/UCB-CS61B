package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;


    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    private class searchnode implements Comparable<searchnode> {
        int v, dict, estimate;
        public searchnode(int v) {
            this.v = v;
            dict = distTo[v];
            estimate = h(v);
        }
        public int compareTo(searchnode that) {
            if (dict + estimate > that.dict + that.estimate) {
                return 1;
            } else if (dict + estimate < that.dict + that.estimate) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int EstimateDist = Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
        return EstimateDist;
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        MinPQ pq = new MinPQ<searchnode>();
        searchnode x = new searchnode(s);
        marked[s] = true;
        announce();
        while (x.v != t) {
            for (int n : maze.adj(x.v)) {
                if (n != edgeTo[x.v]) {
                    marked[n] = true;
                    announce();
                    distTo[n] = x.dict + 1;
                    edgeTo[n] = x.v;
                    announce();
                    pq.insert(new searchnode(n));
                }
            }
            x = (searchnode) pq.delMin();
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

