public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);
        BinaryTrie codingTrie = (BinaryTrie) or.readObject();
        int numberOfSymbols = (int) or.readObject();
        BitSequence bitsequence = (BitSequence) or.readObject();

        String string = "";
        while (bitsequence.length() != 0) {
            Match match = codingTrie.longestPrefixMatch(bitsequence);
            string = string + match.getSymbol();
            bitsequence = new BitSequence(bitsequence.allButFirstNBits(match.getSequence().length()));
        }
        char[] symbols = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            symbols[i] = string.charAt(i);
        }
        FileUtils.writeCharArray(args[1], symbols);
    }
}
