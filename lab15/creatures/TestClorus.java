package creatures;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(1);
        assertEquals(2.00, c.energy, 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.attack(p);
        assertEquals(3.00, c.energy, 0.01);
        c.move();
        assertEquals(2.97, c.energy, 0.01);
        c.move();
        assertEquals(2.94, c.energy, 0.01);
        c.stay();
        assertEquals(2.93, c.energy, 0.01);
        c.stay();
        assertEquals(2.92, c.energy, 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus newc = c.replicate();
        assertNotSame(c, newc);
        assertEquals(1.00, c.energy, 0.01);
        assertEquals(1.00, newc.energy, 0.01);
    }

    @Test
    public void testChoose() {
        Clorus c = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        surrounded.put(Direction.TOP, new Plip());
        surrounded.put(Direction.BOTTOM, new Empty());

        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

        surrounded.put(Direction.TOP, new Impassible());

        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);

        assertEquals(expected, actual);

        /**
        c.energy = 0.5;
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.MOVE, Direction.BOTTOM);

        assertEquals(expected, actual);
         */
    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestClorus.class));
    }
}
