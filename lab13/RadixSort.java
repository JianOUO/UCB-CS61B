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
        /** private int maxlength = 0;
        for (String string : asciis) {
            maxlength = string.length() > maxlength ? string.length() : maxlength;
        }
        String[] asciis2 = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            asciis2[i] = asciis[i];
        }
        for (int i = 0; i < maxlength; i++) {
            //sortHelperLSD(asciis2, i, maxlength);
        }
        return asciis2;
         */
        String[] asciis2 = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            asciis2[i] = asciis[i];
        }
        sortHelperMSD(asciis2,0,asciis2.length - 1,0);
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
                int place = string.length() - index + dist - 1;
                count[string.charAt(place) + 2] += 1;
            }
        }
        for (int i = 1; i < R + 2; i++) {
            count[i] += count[i - 1];
        }
        String[] aux = new String[asciis.length];
        for (String string : asciis) {
            int dist = maxlength - string.length();
            if (dist > index) {
                aux[count[0]++] = string;
            } else {
                int place = string.length() - index + dist - 1;
                aux[count[string.charAt(place) + 1]++] = string;
            }
        }
        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = aux[i];
        }
        return;
    }

    /** public static void main(String[] args) {
        String[] asciis = { "12", "226", "255", "18", "8","188"};
        for (String string : asciis) {
            System.out.print(string + " ");
        }
        System.out.println();
        asciis = sort(asciis);
        for (String string : asciis) {
            System.out.print(string + " ");
        }
        System.out.println();
    }
     */
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
        if (end <= start) {
            return;
        }
        int R = 256;
        int[] count = new int[R + 2];
        for (int i = start; i <= end; i++) {
            if (asciis[i].length() <= index) {
                count[1] += 1;
            } else {
                count[asciis[i].charAt(index) + 2] += 1;
            }
        }
        for (int i = 0; i < R + 1; i++) {
            count[i + 1] += count[i];
        }
        String[] aux = new String[asciis.length];
        for (int i = start; i <= end; i++) {
            if (asciis[i].length() <= index) {
                aux[count[0]++] = asciis[i];
            } else {
                aux[count[asciis[i].charAt(index) + 1]++] = asciis[i];
            }
        }
        for (int i = start; i <= end; i++) {
            asciis[i] = aux[i - start];
        }
        for (int i = 0; i < R; i++) {
            sortHelperMSD(asciis, start + count[i], start + count[i + 1] -1, index + 1);
        }
        return;
    }
}
