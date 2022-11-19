import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> map = new TreeMap<>();
        for (char c : inputSymbols) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }
    public static void main(String[] args) {
        char[] inputSymbols = FileUtils.readFile(args[0]);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(inputSymbols);
        BinaryTrie binTrie = new BinaryTrie(frequencyTable);
        ObjectWriter ow = new ObjectWriter(args[0] + ".huf");
        ow.writeObject(binTrie);
        ow.writeObject(inputSymbols.length);
        Map<Character, BitSequence> LookupTable = binTrie.buildLookupTable();
        List<BitSequence> list = new ArrayList<>();
        for (char c : inputSymbols) {
            list.add(LookupTable.get(c));
        }
        BitSequence bitSequence = BitSequence.assemble(list);
        ow.writeObject(bitSequence);
    }
}
