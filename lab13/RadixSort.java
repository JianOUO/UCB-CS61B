/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int maxlength = 0;
        for (String string : asciis) {
            maxlength = string.length() > maxlength ? string.length() : maxlength;
        }
        String[] asciis2 = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            asciis2[i] = asciis[i];
        }
        for (int i = 0; i < maxlength; i++) {
            sortHelperLSD(asciis2, i, maxlength);
        }
        return asciis2;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index, int maxlength) {
        // Optional LSD helper method for required LSD radix sort
        int R = 256;
        int[] count = new int[R + 2];
        for (String string : asciis) {
            int dist = maxlength - string.length();
            if (dist > index) {
                count[1] += 1;
            } else {
                count[string[index - dist] + 2] += 1;
            }
        }
        for (int i = 1; i < R + 2; i++) {
            count[i] += count[i - 1];
        }
        String[] aux = String[asciis.length];
        for (String string : asciis) {
            int dist = maxlength - string.length();
            if (dist > index) {
                aux[count[0]++] = string;
            } else {
                aux[count[string[index - dist] + 1]++] = string;
            }
        }
        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = aux[i];
        }
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
