package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period, state;
    private double factor;
    public AcceleratingSawToothGenerator(int period, double factor) {
        this.factor = factor;
        this.period = period;
        state = 0;
    }
    public double next() {
        state = (state + 1) % period;
        if (state == 0) {
            period = Math.round((float) (factor * period));
        }
        return state * 2.0 / period - 1;
    }
}
