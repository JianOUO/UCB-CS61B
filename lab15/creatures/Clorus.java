package creatures;
import huglife.*;

import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        energy = e;
        r = 34;
        g = 0;
        b = 231;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        return new Color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        energy -= 0.03;
    }

    public void stay() {
        energy -= 0.01;
    }

    public Clorus replicate() {
        energy *= 0.5;
        return new Clorus(energy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empty = getNeighborsOfType(neighbors, "empty");
        if (empty.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else {
            List<Direction> plip = getNeighborsOfType(neighbors, "plip");
            if (plip.size() > 0) {
                Direction attackdir = HugLifeUtils.randomEntry(plip);
                return new Action(Action.ActionType.ATTACK, attackdir);
            } else {
                if (energy >= 1.0) {
                    Direction replicatedir = HugLifeUtils.randomEntry(empty);
                    return new Action(Action.ActionType.REPLICATE, replicatedir);
                }
            }
        }
        Direction movedir = HugLifeUtils.randomEntry(empty);
        return new Action(Action.ActionType.MOVE, movedir);
    }
}
