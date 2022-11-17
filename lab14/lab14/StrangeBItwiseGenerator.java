package lab14;

import edu.princeton.cs.introcs.StdRandom;
import lab14lib.Generator;

public class StrangeBItwiseGenerator implements Generator {

    private int period, state, weirdState;
    public StrangeBItwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }
    public double next() {
        state = state + 1;
        weirdState = state & (state >> 7) % period;
        return weirdState * 2.0 / period - 1;
    }
}
