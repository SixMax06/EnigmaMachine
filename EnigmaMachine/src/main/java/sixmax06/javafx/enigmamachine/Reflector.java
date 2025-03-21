package sixmax06.javafx.enigmamachine;

import java.util.TreeMap;

public class Reflector {
    private final TreeMap <Integer, Integer> reflectedAlphabet;

    public Reflector(String alphabet) {
        this.reflectedAlphabet = new TreeMap<>();

        for (int i = 0; i < 26; i++)
            this.reflectedAlphabet.put(i, alphabet.charAt(i) - 'A');
    }

    public int getReflectedCharacter(int key) {
        return this.reflectedAlphabet.get(key);
    }

    public TreeMap<Integer, Integer> getReflectedAlphabet() {
        return this.reflectedAlphabet;
    }

    public String toString() {
        return this.reflectedAlphabet.toString();
    }
}
