package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private searchnode goalnode;

    private class searchnode implements Comparable<searchnode>{
        WorldState currentstate;
        int numberofmove;
        searchnode previousnode;

        public searchnode(WorldState s, int n, searchnode p) {
            currentstate = s;
            numberofmove = n;
            previousnode = p;
        }

        public int compareTo(searchnode n) {
            if (numberofmove + currentstate.estimatedDistanceToGoal() > n.numberofmove + n.currentstate.estimatedDistanceToGoal()) {
                return 1;
            } else if (numberofmove + currentstate.estimatedDistanceToGoal() < n.numberofmove + n.currentstate.estimatedDistanceToGoal()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public Solver(WorldState initial) {
        MinPQ pq = new MinPQ<searchnode>();
        searchnode X = new searchnode(initial, 0, null);
        if (!X.currentstate.isGoal()) {
            for (WorldState s : X.currentstate.neighbors()) {
                pq.insert(new searchnode(s, X.numberofmove + 1, X));
            }
            X = (searchnode) pq.delMin();
        }
        while (!X.currentstate.isGoal()) {
            for (WorldState s : X.currentstate.neighbors()) {
                if(!s.equals(X.previousnode.currentstate)) {
                    pq.insert(new searchnode(s, X.numberofmove + 1, X));
                }
            }
            X = (searchnode) pq.delMin();
        }
        goalnode = X;
    }
    public int moves() {
        return goalnode.numberofmove;
    }

    public Iterable<WorldState> solution() {
        Stack<WorldState> stack = new Stack<>();
        for (searchnode n = goalnode; n != null; n = n.previousnode) {
            stack.push(n.currentstate);
        }
        return stack;
    }
}
