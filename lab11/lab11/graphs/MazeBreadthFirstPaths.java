package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

import java.util.AbstractQueue;
import java.util.LinkedList;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s, t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> queue;
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        // Add more variables here!
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        queue = new Queue<>();
        distTo[s] = 0;
        edgeTo[s] = s;
        queue.enqueue(s);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        marked[v] = true;
        announce();

        if (v == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }

        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
                queue.enqueue(w);
            }
        }

        bfs(queue.dequeue());
        if (targetFound) {
            return;
        }
    }


    @Override
    public void solve() {
        bfs(queue.dequeue());
    }
}

