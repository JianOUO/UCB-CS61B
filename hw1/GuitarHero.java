import synthesizer.GuitarString;

public class GuitarHero {
    public static void main(String[] args) {
        synthesizer.GuitarString[] strings = new synthesizer.GuitarString[37];
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        for (int i = 0; i < strings.length; i++) {
            strings[i] = new synthesizer.GuitarString(440 * Math.pow(2.0, (i - 24) / 12.0));
        }
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                strings[index].pluck();
            }

            double sample = 0;
            for (synthesizer.GuitarString item : strings) {
                sample += item.sample();
            }

            StdAudio.play(sample);

            for (synthesizer.GuitarString item : strings) {
                item.tic();
            }
        }
    }
}
